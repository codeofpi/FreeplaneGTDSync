// @ExecutionModes({ON_SINGLE_NODE})
//==============================================================================
//  GTD sync with todo.txt
//
//  Groovy script to synchronise a Freeplane|GTD mind map with todo.txt. The 
//  catch is that you can make your Next Action list available to your 
//  smartphone or tablet and adjust it from there.
//
//  For further information consult GTD Sync Help: quick start (Alt+F1) or the
//  GTD Sync Wiki: https://sourceforge.net/p/gtdsync/wiki/

// Imports
import groovy.swing.SwingBuilder
import gtdSync.NextActionHandler
import gtdSync.Support
import java.awt.Color
import java.awt.Container
import java.awt.Dimension
import java.awt.FlowLayout
import java.net.URI
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.Date
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants
import javax.swing.UIManager
import org.freeplane.plugin.script.proxy.Proxy

//  Todo.txt directory and files
//  Path to location of todo.txt 
String strTodoDirectory
//  Todo.txt file
String strTodoTxtFile = "todo.txt"
File fileTodoTxt
//  Backup of todo.txt before synchronisation
String strTodoTxtBackup = "todo.bak.txt"
File fileTodoTxtBackup
def readerTodoTxt
//  Snapshot of todo.txt after synchronisation used to determine changes in todo.txt
//  during next synchronisation.
String strTodoTxtHistory = "todo.history.txt"
File fileTodoTxtHistory
def readerTodoTxtHistory
//  Backup of todo.txt.history before synchronisation
String strTodoTxtHistoryBackup = "todo.history.bak.txt"
File fileTodoTxtHistoryBackup
// Writer for todo.txt and todo.txt.history
def writerTodoTxt = []

// Collections of next actions
def nahsHistory = []
def nahsExport = []

// Objects to handle (historic) Next Actions
NextActionHandler nahNextAction
NextActionHandler nahNextActionOld

// Mind map nodes necessary for updating
Proxy.Node nodeRoot = node.map.root
def nodesFound = []
def nodesSelected = []
Proxy.Node nodeNew
def nodesProject = [:]
Proxy.Node nodeParent
Proxy.Node nodeAction
def nodesProjectExport =[]

// Exported todo.txt lines
String strTodoTxt = ""

// Support functions and checking/getting the custom icons
Support s = new Support(nodeRoot)

// Translations: Strings with information or warnings on the processing of todo.txt
String strNotImportedFirstSync = textUtils.getText('gtdSyncNotImportedFirstSync',
	'NOT IMPORTED: This todo.txt line contains a node ID, like it has been exported from the\
 GTD mind map during a previous synchronisation. However, this is not possible since this is the\
 first synchronisation.')
String strNotImportedNodeMissing = textUtils.getText('gtdSyncNotImportedNodeMissing',
	'NOT IMPORTED: This existing next action could not be found in the GTD mind map and because\
 of that it was not possible to apply the following changes:')
String strNotImportedNodeChanged = textUtils.getText('gtdSyncNotImportedNodeChanged',
	'NOT IMPORTED: The corresponding next action in the mind map has also been changed since last\
 synchronisation. Based on the overview below you can change the next action in the mind map manually.')
String strImported = textUtils.getText('gtdSyncImported','IMPORTED:')
String strTodoTxtMissing = ""
String strTodoTxtMissingTrue = textUtils.getText('gtdSyncResultTodoTxtMissing',
 '<p><b>Warning:</b> there was no todo.txt present. It was expected to be present since this is\
 not the first synchronisation. If there were any new or changed actions, they have been lost\
 with the todo.txt file.</p>')
String strTodoTxtMissingFirstSync = ""
String strTodoTxtMissingFirstSyncTrue = textUtils.getText('gtdSyncResultTodoTxtMissingFirstSync',
	'<p>There was not todo.txt present and thus no next actions have been imported.</p>')
String strNextActionsTodoTxt = textUtils.getText('gtdSyncResultNextActionsTodoTxt',
 '<p>A total of {0} next actions from todo.txt have been processed of\
 which:<p><ul style={2}><li>{1} new next actions')
String strNextActionsTodoTxtNewWithWarning = textUtils.getText(
	'gtdSyncResultNextActionsTodoTxtNewWithWarning',
	', of which:<ul style={1}><li>{0} next actions with remarks and/or corrections</li></ul>')
String strNextActionsTodoTxtExisting = textUtils.getText(
	'gtdSyncResultNextActionsTodoTxtExisting',
	'</li><li>{1} of {0} existing next actions have been changed')
String strNextActionsTodoTxtExistingChanged = textUtils.getText(
	'gtdSyncResultNextActionsTodoTxtExistingChanged',', of which:<ul style={0}>')
String strNextActionsTodoTxtExistingWithWarning = textUtils.getText(
	'gtdSyncResultNextActionsTodoTxtExistingWithWarning',
	'<li>{0} next actions with remarks and/or corrections</li>')
String strNextActionsTodoTxtExistingNotImported = textUtils.getText(
	'gtdSyncResultNextActionsTodoTxtExistingNotImported',
	'<li>{0} next actions have not been imported</li>')

// Translations: Strings with information or warnings on the processing of the history
// snapshot file
String strTodoTxtHistoryMissing = ""
String strTodoTxtHistoryMissingTrue = textUtils.getText('gtdSyncResultTodoTxtHistoryMissing',
 '<br><p><b>Warning:</b> the snapshot of the exported next actions during last synchronisation\
 is missing. To prevent loss of potential changes it has been assumed that all existing next\
 actions in todo.txt have changed since last synchronisation.</p>')
String strDifferentCounts = textUtils.getText('gtdSyncResultDifferentCounts',
 '<p><b>Warning:</b> the number of exported next actions in last synchronisation, {0}, differs\
 from the number of existing actions in todo.txt, {1}. Possibly next actions or node id were\
 removed by accident.</p>')
String strTodoTxtHistoryWarnings = textUtils.getText('gtdSyncResultTodoTxtHistoryWarnings',
 '<p><b>Warning:</b> there were {0} remarks and/or corrections reading the next actions\
 exported last synchronisation. This could be caused by manually changing the file {1}. This\
 file should not be changed.</p>')

// Translations: String that indicates whether it is the first synchronisation
String strFirstSync = ""
String strFirstSyncTrue = textUtils.getText('gtdSyncResultFirstSync',
	'<br><p>This was the first synchronisation.</p>')

// Last sync date and translated string that indicates whether it is missing
Date dateLastSync
String strLastSyncDateMissing = ""
String strLastSyncDateMissingTrue = textUtils.getText('gtdSyncResultLastSyncDateMissing',
 '<p><b>Warning:</b> the date of the last synchronisation was missing. Because of this it\
 could not be determined whether next actions in the mind map have changed in the meantime. It\
 is assumed that all of them have changed and out of precaution potential updates from\
 todo.txt have been skipped. If so, it is indicated that the changed next action have\
 not been imported and there is a reference to the details in the logfile.</p>')

// Translations: Strings with information or warnings on the export to todo.txt
String strNextActionsExported = textUtils.getText('gtdSyncResultNextActionsExported',
	'<br><p>{0} next actions were exported to todo.txt')
String strNextActionsExportedList = textUtils.getText('gtdSyncResultNextActionsExportedList',
	', with:<ul style={0}>')
String strProjectsCorrected = textUtils.getText('gtdSyncResultProjectsCorrected',
	'<li>{0} corrected projects</li>')
String strNextActionsExportedWithWarning = textUtils.getText(
	'gtdSyncResultNextActionsExportedWithWarning', '<li>{0} corrected next actions</li>')

// Translations: String with information on logging
String strLogNotImported = textUtils.getText('gtdSyncResultLogNotImported',
 '<br><p>Some existing next actions in the GTD mind map have not been updated with the changes\
 from todo.txt. You can review these changes in the Freeplane logfile and change the next\
 actions manually if necessary. The logfile may also contain information regarding correction on\
 next actions.</p>')
String strLogCorrections = textUtils.getText('gtdSyncResultLogCorrections',
	'<br><p>For details on corrected next actions, see the Freeplane logfile.</p>')
String strNoLog = textUtils.getText('gtdSyncResultNoLog',
	'<br><p>There are no skipped or corrected next actions.</p>')
String strLogButtonText = textUtils.getText("gtdSyncResultLogButtonText",
	"<HTML><CENTER>Consult the Freeplane logfile</CENTER></HTML>")
// URI for Freeplane logfile
URI uriFreeplaneLog = new File(logger.logDirectory + "\\log.0").toURI()

// Translations: Strings for path dialogue
String strPathDialogueTitle = textUtils.getText('gtdSyncPathDialogueTitle',
	'Select folder for todo.txt. If you do not run GTD sync from your GTD mind map, cancel!')
String strPathSelect = textUtils.getText('gtdSyncPathSelect','Select')
String strPathSelectToolTip = textUtils.getText('gtdSyncPathSelectToolTip',
	'Select a folder where todo.txt is stored or is going to be stored.')
String strPathCancel = textUtils.getText('gtdSyncPathCancel','Cancel')
String strPathCancelToolTip = textUtils.getText('gtdSyncPathCancelToolTip',
	'Cancel to prevent that GTD is run on the wrong mind map.')
String strPathLookIn = textUtils.getText('gtdSyncPathLookIn','Look in:')
String strPathFilesOfType = textUtils.getText('gtdSyncPathFilesOfType','Files of Type')
String strPathAllFiles = textUtils.getText('gtdSyncPathAllFiles','All Files')
String strPathUpFolderToolTip = textUtils.getText('gtdSyncUpFolderToolTip','Up One Level')
String strPathNewFolderToolTip = textUtils.getText('gtdSyncNewFolderToolTip',
	'Create New Folder')

//  Translations: Strings for final message
String strTitle = textUtils.getText('gtdSyncResultTitle','Result of GTD synchronisation')
String strCancellation = textUtils.getText('gtdSyncResultCancellation',
	'Synchronisation is cancelled because no folder for todo.txt has been specified.')
String strMessage = '<html><body><div style="width: 400px">' +
	textUtils.getText('gtdSyncResultCompleted','<p>Synchronisation completed.</p>')
String strMessagePart = ""
String strMessageList = ""
// Style to prevent space between paragraphs and lists.
String strMessageListStyle = "\"margin-top: 0 margin-bottom: 0\""

//  Dialogue in case path has not been stored in mind map
def jfcTodoTxtPath = new JFileChooser(
    dialogTitle:strPathDialogueTitle,
    fileSelectionMode:JFileChooser.DIRECTORIES_ONLY,
// Commented out because of defect 13 https://sourceforge.net/p/gtdsync/tickets/13/
//    currentDirectory:new java.io.File(System.getenv('USERPROFILE')),
    approveButtonText:strPathSelect,
    approveButtonToolTipText:strPathSelectToolTip
)
UIManager.put("FileChooser.cancelButtonText",strPathCancel)
UIManager.put("FileChooser.cancelButtonToolTipText",strPathCancelToolTip)
UIManager.put("FileChooser.lookInLabelText",strPathLookIn)
UIManager.put("FileChooser.filesOfTypeLabelText",strPathFilesOfType)
UIManager.put("FileChooser.acceptAllFileFilterText",strPathAllFiles)
UIManager.put("FileChooser.upFolderToolTipText",strPathUpFolderToolTip)
UIManager.put("FileChooser.newFolderToolTipText",strPathNewFolderToolTip)

// Final dialogue
JFrame frameSyncResult = new JFrame(strTitle)
frameSyncResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
Container container = frameSyncResult.getContentPane()
container.setLayout(new FlowLayout(FlowLayout.LEFT))
JButton button = new JButton()

//  Counters
//  Number of next actions in history snapshot
int intNextActionsHistory = 0
//  Number of next actions in history snapshot with warning
int intNextActionsHistoryWithWarning = 0
//  Number of next actions in todo.txt
int intNextActionsTodoTxt = 0
//  Number of new next actions in todo.txt
int intNextActionsTodoTxtNew = 0
//  Number of new next actions in todo.txt with warning
int intNextActionsTodoTxtNewWithWarning = 0
//  Number of existing next actions in todo.txt
int intNextActionsTodoTxtExisting = 0
//  Number of existing next actions in todo.txt with warning
int intNextActionsTodoTxtExistingChanged = 0
//  Number of existing next actions in todo.txt with warning
int intNextActionsTodoTxtExistingWithWarning = 0
//  Number of existing next actions in todo.txt not imported
int intNextActionsTodoTxtExistingNotImported = 0
//  Number of next actions exported to todo.txt
int intNextActionsExported = 0
//  Number of projects corrected during export
int intProjectCorrected = 0
//  Number of next actions exported to todo.txt with warning
int intNextActionsExportedWithWarning = 0

// Start script
// Check difference in language
if (Support.blnDifferenceInLanguage(nodeRoot)) return
// Save the GTD mind map
node.map.save(true)
//  If path to todo.txt is present in root node, process root node attributes
if (nodeRoot['todoTxtPath'].toString()) {
    strTodoDirectory = nodeRoot['todoTxtPath'].toString()
	if (nodeRoot['dateLastSync'].toString()) {
	    dateLastSync = new Date(nodeRoot['dateLastSync'].num)
//  dateLastSync should be present after the first sync, but just in case it is not...
	}
	else {
		dateLastSync = new Date(0)
		strLastSyncDateMissing = strLastSyncDateMissingTrue
	}
// If the history snapshot of todo.txt exist, parse it in a next action collection
// and make a backup of it.
	fileTodoTxtHistory = new File(strTodoDirectory + strTodoTxtHistory)
	fileTodoTxtHistoryBackup = new File(strTodoDirectory + strTodoTxtHistoryBackup)
	if (fileTodoTxtHistory.exists()) {
//  The history snapshot file is parsed into a collection
		readerTodoTxtHistory = new InputStreamReader(new FileInputStream(strTodoDirectory
			+ strTodoTxtHistory), 'UTF-8')
		readerTodoTxtHistory.eachLine {
// Exclude lines without any text
			if (it.trim()) {
				nahsHistory += new NextActionHandler(it)
			}
		}
// Write warnings to Freeplane log file and count processed lines
		nahsHistory.each {
			it.logAndCounters(strTodoTxtHistory)
		}
// Take counters history file over for final report
		intNextActionsHistory = NextActionHandler.intNextActionsExisting
		intNextActionsHistoryWithWarning = NextActionHandler.intNextActionsExistingWithWarning +
			NextActionHandler.intNextActionsNewWithWarning
		NextActionHandler.intNextActionsTotal = 0
		NextActionHandler.intNextActionsNew = 0
		NextActionHandler.intNextActionsNewWithWarning = 0
		NextActionHandler.intNextActionsExisting = 0
		NextActionHandler.intNextActionsExistingChanged = 0
		NextActionHandler.intNextActionsExistingWithWarning = 0
		NextActionHandler.intNextActionsExistingNotImported = 0
//  Make a backup of the history snapshot file
		if (fileTodoTxtHistoryBackup.exists()) {
			fileTodoTxtHistoryBackup.delete()
		}
		fileTodoTxtHistory.renameTo(strTodoDirectory + strTodoTxtHistoryBackup)
//  The snapshot file should be present after the first sync, but just in case it is not...
	}
	else {
// The following string is now true and is included as information in the final message
		strTodoTxtHistoryMissing = strTodoTxtHistoryMissingTrue
	}
}
else {
//  If path is not present, ask for it and store it in root node.
//  It also means the GTD mind map has not been synced before
    if (jfcTodoTxtPath.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
        strTodoDirectory = jfcTodoTxtPath.selectedFile.toString() + "\\"
        nodeRoot['todoTxtPath'] = strTodoDirectory
// The following string is now true and is included as information in the final message
        strFirstSync = strFirstSyncTrue
//  If the path is not given cancel the synchronisation and display an information message
    }
	else {
        ui.informationMessage(ui.frame, strCancellation, strTitle)
        return
    }
}
// If todo.txt exists, parse the lines and update the GTD mind map
fileTodoTxt = new File(strTodoDirectory + strTodoTxtFile)
fileTodoTxtBackup = new File(strTodoDirectory + strTodoTxtBackup)
if (fileTodoTxt.exists()) {
//  Read the todo.txt file as UTF-8
	readerTodoTxt = new InputStreamReader(new FileInputStream(strTodoDirectory
		+ strTodoTxtFile), 'UTF-8')
// Process the file line by line
	readerTodoTxt.eachLine {
// Exclude lines without any text
		if (it.trim()) {
// Parse the line into a NextActionHandler object
			nahNextAction = new NextActionHandler(it)
// Process existing next actions
			if (nahNextAction.strNodeID) {
// If it is the first synchronisation, existing next actions are not possible
				if (strFirstSync) {
					nahNextAction.strMessage += "\n- " + strNotImportedFirstSync
					nahNextAction.blnChanged = true
					nahNextAction.blnNotImported = true
				}
				else {
// If the historic snapshot is missing assume all attributes have changed
					if (strTodoTxtHistoryMissing) {
						nahNextAction.defaultHistoricValues()
					}
					else {
// Find historic record in historic snapshot file
						nahsHistory.each {
							if (it.strNodeID == nahNextAction.strNodeID) {
								nahNextActionOld = it
							}
						}
// Assign historic values for comparision later
						if (nahNextActionOld) {
							nahNextAction.strNameOld = nahNextActionOld.strName
							nahNextAction.blnPriorityOld = nahNextActionOld.blnPriority
							nahNextAction.dateDoneOld = nahNextActionOld.dateDone
							nahNextAction.dateCreatedOld = nahNextActionOld.dateCreated
							nahNextAction.strWhereOld = nahNextActionOld.strWhere
							nahNextAction.strWhoOld = nahNextActionOld.strWho
							nahNextAction.dateThresholdOld = nahNextActionOld.dateThreshold
							nahNextAction.dateWhenOld = nahNextActionOld.dateWhen
							nahNextAction.strProjectOld = nahNextActionOld.strProject
							nahNextAction.uriLinkOld = nahNextActionOld.uriLink
							nahNextActionOld = null
						}
// If the historic record is not found assume all attributes have changed
						else {
							nahNextAction.defaultHistoricValues()
						}
					}
// Determine and record whether attributes have changed
					if (nahNextAction.strName != nahNextAction.strNameOld) {
						nahNextAction.strNameChanged = "\n* " + nahNextAction.strNameLabel + ":"
					}
					if (nahNextAction.blnPriority != nahNextAction.blnPriorityOld) {
						nahNextAction.strPriorityChanged = "\n* " + nahNextAction.strPriorityLabel + ":"
					}
					if (nahNextAction.dateDone != nahNextAction.dateDoneOld) {
						nahNextAction.strDoneChanged = "\n* " + s.strDoneLabel + ":"
					}
					if (nahNextAction.dateCreated != nahNextAction.dateCreatedOld) {
						nahNextAction.strCreatedChanged = "\n* " + nahNextAction.strCreatedLabel + ":"
					}
					if (nahNextAction.strWhere != nahNextAction.strWhereOld) {
						nahNextAction.strWhereChanged = "\n* " + s.strWhereLabel + ":"
					}
					if (nahNextAction.strWho != nahNextAction.strWhoOld) {
						nahNextAction.strWhoChanged = "\n* " + s.strWhoLabel + ":"
					}
					if (nahNextAction.dateThreshold != nahNextAction.dateThresholdOld) {
						nahNextAction.strThresholdChanged = "\n* " + s.strThresholdLabel + ":"
					}
					if (nahNextAction.dateWhen != nahNextAction.dateWhenOld) {
						nahNextAction.strWhenChanged = "\n* " + s.strWhenLabel + ":"
					}
					if (nahNextAction.strProject != nahNextAction.strProjectOld) {
						nahNextAction.strProjectChanged = "\n* " + s.strProjectLabel + ":"
					}
					if (nahNextAction.uriLink != nahNextAction.uriLinkOld) {
						nahNextAction.strLinkChanged = "\n* " + nahNextAction.strLinkLabel + ":"
					}
// If there are any updatable changes start the update process
					if (nahNextAction.strNameChanged + nahNextAction.strPriorityChanged +
						nahNextAction.strDoneChanged + nahNextAction.strWhereChanged +
						nahNextAction.strWhoChanged + nahNextAction.strThresholdChanged +
						nahNextAction.strWhenChanged + nahNextAction.strLinkChanged) {
						nahNextAction.blnChanged = true
// Lookup corresponding node in GTD mind map
						nodesFound = c.find{ it.nodeID == nahNextAction.strNodeID}
// If not found, it is not possible to update
						if (nodesFound[0] == null) {
							nahNextAction.strMessage += "\n- " + strNotImportedNodeMissing
							nahNextAction.listChangedAttributes()
							nahNextAction.blnNotImported = true
						}
						else {
// If the node als has been changed since last update, do not update
							if (nodesFound[0].lastModifiedAt > dateLastSync) {
								nahNextAction.strMessage += "\n- " + strNotImportedNodeChanged
								nahNextAction.listChangedAttributes()
								nahNextAction.blnNotImported = true
							}
							else {
// List changes which are not updatable
								if (nahNextAction.strCreatedChanged + nahNextAction.strProjectChanged) {
									nahNextAction.notUpdatableChanges()
								}
								nahNextAction.strImportInfo += "\n- " + strImported
								nahNextAction.listChangedAttributes()
// Update changes in mind map
								if (nahNextAction.strNameChanged) {
									nodesFound[0].text = nahNextAction.strName
								}
								if (nahNextAction.strPriorityChanged) {
									if (nahNextAction.blnPriority) {
										s.ReplaceIcons(nodesFound[0], s.strIconNextAction, 
											s.strIconToday)
									}
									else {
										s.ReplaceIcons(nodesFound[0], s.strIconNextAction)
									}
								}
								if (nahNextAction.strDoneChanged) {
									s.ReplaceIcons(nodesFound[0], s.strIconDone)
									nodesFound[0][(s.strDoneLabel)] =
									s.dateFormat.format(nahNextAction.dateDone) 
								}
								if (nahNextAction.strWhereChanged) {
									nodesFound[0][(s.strWhereLabel)] = nahNextAction.strWhere
								}
								if (nahNextAction.strWhoChanged) {
									nodesFound[0][(s.strWhoLabel)] = nahNextAction.strWho
								}
								if (nahNextAction.strThresholdChanged) {
									if (nahNextAction.dateThreshold) {
										nodesFound[0][(s.strThresholdLabel)] =
										s.dateFormat.format(nahNextAction.dateThreshold)
									}
									else { nodesFound[0][(s.strThresholdLabel)] = "" }
								}
								if (nahNextAction.strWhenChanged) {
									if (nahNextAction.dateWhen) {
										nodesFound[0][(s.strWhenLabel)] =
										s.dateFormat.format(nahNextAction.dateWhen)
									}
									else { nodesFound[0][(s.strWhenLabel)] = "" }
								}
								if (nahNextAction.strLinkChanged) {
									nodesFound[0].link.uri = nahNextAction.uriLink
								}
// Unfold branch up to changed node and add it to selection
								nahNextAction.unfoldBranch(nodesFound[0])
								nodesSelected += nodesFound[0]
							}
							nodesFound = []
						}
					}
					else {
// Message if there only changes that are not updatable
						if (nahNextAction.strCreatedChanged + nahNextAction.strProjectChanged) {
							nahNextAction.blnChanged = true
							nahNextAction.notUpdatableChanges()
						}
					}
				}
// Process new next actions
			}
			else {
// Create "New" node if not present
				nodeNew = nahNextAction.checkNode(nodeNew, nodeRoot, nahNextAction.strNewLabel, "")
// If the next action relates to a project, create a project node, if not present
				if (nahNextAction.strProject) {
					nodesProject[(nahNextAction.strProject)] = nahNextAction.checkNode(
						nodesProject[(nahNextAction.strProject)],nodeNew,nahNextAction.strProject,
						s.strIconProject)
					nodeParent = nodesProject[(nahNextAction.strProject)]
				}
				else { nodeParent = nodeNew }
				nodeAction = nodeParent.createChild(nahNextAction.strName)
				if (nahNextAction.dateDone) {
					s.ReplaceIcons(nodeAction, s.strIconDone)
				}
				else {
					if (nahNextAction.blnPriority) {
						s.ReplaceIcons(nodeAction, s.strIconNextAction, s.strIconToday)
					}
					else {
						s.ReplaceIcons(nodeAction, s.strIconNextAction)
					}
				}
				nodeAction.createdAt = nahNextAction.dateCreated
				s.NextActionAttributes(nodeAction, nahNextAction.strWhere, nahNextAction.strWho,
					nahNextAction.dateThreshold, nahNextAction.dateWhen, nahNextAction.dateDone)
				if (nahNextAction.uriLink) {
					nodeAction.link.uri = nahNextAction.uriLink
				}
			}
			nahNextAction.logAndCounters(strTodoTxtFile)
			nahNextAction = null
		}
	}

// Collect and reset the counters
	intNextActionsTodoTxt = NextActionHandler.intNextActionsTotal
	intNextActionsTodoTxtNew = NextActionHandler.intNextActionsNew
	intNextActionsTodoTxtNewWithWarning = NextActionHandler.intNextActionsNewWithWarning
	intNextActionsTodoTxtExisting = NextActionHandler.intNextActionsExisting
	intNextActionsTodoTxtExistingChanged = NextActionHandler.intNextActionsExistingChanged
	intNextActionsTodoTxtExistingWithWarning = NextActionHandler.intNextActionsExistingWithWarning
	intNextActionsTodoTxtExistingNotImported = NextActionHandler.intNextActionsExistingNotImported
	NextActionHandler.intNextActionsTotal = 0
	NextActionHandler.intNextActionsNew = 0
	NextActionHandler.intNextActionsNewWithWarning = 0
	NextActionHandler.intNextActionsExisting = 0
	NextActionHandler.intNextActionsExistingChanged = 0
	NextActionHandler.intNextActionsExistingWithWarning = 0
	NextActionHandler.intNextActionsExistingNotImported = 0
	
// Select the changed nodes
	c.selectMultipleNodes(nodesSelected)

//  Make a backup of the todo.txt file.
	if (fileTodoTxtBackup.exists()) {
		fileTodoTxtBackup.delete()
	}
	fileTodoTxt.renameTo(strTodoDirectory + strTodoTxtBackup)
}
else {
// Feedback that the todo.txt was not present at the first sync (which is an allowed situation).
	if (strFirstSync) {
// The following string is now true and is included as information in the final message
		strTodoTxtMissingFirstSync = strTodoTxtMissingFirstSyncTrue
// The todo.txt file should be present after the first sync, but just in case it is not...
	}
	else {
// The following string is now true and is included as information in the final message
		strTodoTxtMissing = strTodoTxtMissingTrue
	}
}

// List all projects in the GTD mind map and remove spaces from project names
nodesProjectExport = NextActionHandler.findProjects(nodeRoot, s.strIconProject)

// Find all next action nodes and turn them into NextActionHandler objects:
nahsExport = NextActionHandler.findNextActions(nodeRoot, nodesProjectExport, s.strIconNextAction,
	s.strIconToday)
// Put todo.txt lines into one string
nahsExport.each {
	strTodoTxt += it.strTodoTxtLine + "\n"
}

//  Write next actions to todo.txt and to new snapshot file
writerTodoTxt[0] = new OutputStreamWriter(new FileOutputStream(strTodoDirectory
	+ strTodoTxtFile),"UTF-8")
writerTodoTxt[1] = new OutputStreamWriter(new FileOutputStream(strTodoDirectory
	+ strTodoTxtHistory),"UTF-8")
writerTodoTxt.each { it.write(strTodoTxt) }
writerTodoTxt.each { it.close() }

// Collect and reset the counters for export
intProjectCorrected = NextActionHandler.intNextActionsNewWithWarning
intNextActionsExported = NextActionHandler.intNextActionsExisting
intNextActionsExportedWithWarning = NextActionHandler.intNextActionsExistingWithWarning
NextActionHandler.intNextActionsTotal = 0
NextActionHandler.intNextActionsNew = 0
NextActionHandler.intNextActionsNewWithWarning = 0
NextActionHandler.intNextActionsExisting = 0
NextActionHandler.intNextActionsExistingChanged = 0
NextActionHandler.intNextActionsExistingWithWarning = 0
NextActionHandler.intNextActionsExistingNotImported = 0


// Change last sync date in root node
nodeRoot['dateLastSync'] = new Date().time

// Build up and display final message

// In case it is the first synchronisation
strMessage += strFirstSync

// Messages on reading history snapshot
if (strTodoTxtHistoryMissing) {
	strMessage += strTodoTxtHistoryMissing
}
else {
	if (intNextActionsHistoryWithWarning > 0) {
		strMessagePart = MessageFormat.format(strTodoTxtHistoryWarnings,
			intNextActionsHistoryWithWarning, strTodoTxtHistory)
	}
	if (intNextActionsHistory != intNextActionsTodoTxtExisting && !(strTodoTxtMissing)) {
		strMessagePart += MessageFormat.format(strDifferentCounts, intNextActionsHistory,
			intNextActionsTodoTxtExisting)
	}
// If there was any message with on history snapshot, put a white space in front of it
	if (strMessagePart) {
		strMessage += "<br>" + strMessagePart
		strMessagePart = ""
	}
}
// Messages on processing todo.txt
strMessagePart = strLastSyncDateMissing
// If todo.txt was missing it is no necessary to display additional messages with regard to 
// the processing of todo.txt.
if (strTodoTxtMissingFirstSync || strTodoTxtMissing) {
	strMessagePart = strTodoTxtMissingFirstSync
	strMessagePart += strTodoTxtMissing
}
else {
// Display total number of next actions in todo.txt and number of new next actions
	strMessagePart += MessageFormat.format(strNextActionsTodoTxt, intNextActionsTodoTxt,
		intNextActionsTodoTxtNew, strMessageListStyle)
// Display the number of new next actions with warnings in a nested list, if any.
	if (intNextActionsTodoTxtNewWithWarning > 0) {
		strMessagePart += MessageFormat.format(strNextActionsTodoTxtNewWithWarning,
			intNextActionsTodoTxtNewWithWarning, strMessageListStyle)
	}
// Display the number of existing next actions, total number and number changed.
	strMessagePart += MessageFormat.format(strNextActionsTodoTxtExisting,
		intNextActionsTodoTxtExisting, intNextActionsTodoTxtExistingChanged)
// Display the number of changed next actions with warnings in a nested list, if any
	if (intNextActionsTodoTxtExistingWithWarning > 0) {
		strMessageList = MessageFormat.format(strNextActionsTodoTxtExistingWithWarning,
			intNextActionsTodoTxtExistingWithWarning)
	}
// Display the number of changed next actions not imported in a nested list, if any
	if (intNextActionsTodoTxtExistingNotImported > 0) {
		strMessageList += MessageFormat.format(strNextActionsTodoTxtExistingNotImported,
			intNextActionsTodoTxtExistingNotImported)
	}
// Display the nested list, if there are any changed next actions with warnings or not imported.
	if (strMessageList) {
		strMessagePart += MessageFormat.format(strNextActionsTodoTxtExistingChanged,
			strMessageListStyle) + strMessageList + "</ul>"
		strMessageList = ""
	}
// Close the list	
	strMessagePart += "</li></ul>"
}
// Add a white space before this message part.
strMessage += "<br>" + strMessagePart
strMessagePart = ""

// Messages on export to todo.txt
// Display number of next actions exported to todo.txt
strMessage += MessageFormat.format(strNextActionsExported, intNextActionsExported)
// Display the corrected projects, if any
if (intProjectCorrected > 0) {
	strMessageList = MessageFormat.format(strProjectsCorrected,
		intProjectCorrected)
}
// Display number of exported next actions with warnings, if any.
if (intNextActionsExportedWithWarning > 0) {
	strMessageList += MessageFormat.format(strNextActionsExportedWithWarning,
		intNextActionsExportedWithWarning)
}
// Display the nested list, if there are any changed next actions with warnings or not imported.
if (strMessageList) {
	strMessage += MessageFormat.format(strNextActionsExportedList,
		strMessageListStyle) + strMessageList + "</ul>"
	strMessageList = ""
}
// Close the paragraph of this part.
strMessage += "</p>"

// Reference to log
// If there were any next actions not imported, display information advising the user.
if (intNextActionsTodoTxtExistingNotImported > 0) {
	strMessage += strLogNotImported
}
// If there were any warnings, display information advising the user.
else {
	if (intNextActionsHistoryWithWarning + intNextActionsTodoTxtNewWithWarning +
		intNextActionsTodoTxtExistingWithWarning + intNextActionsExportedWithWarning +
		intProjectCorrected > 0) {
		strMessage += strLogCorrections
	}
// ... else let the user know there were not any irregularities.
	else {
		strMessage += strNoLog
	}
}
// Close HTML of the message
strMessage += "</div></body></html>"
// Add the message to the frame
container.add(new JLabel(strMessage))
// If there are any irregularities in the log, display a button for viewing the log file
if (intNextActionsHistoryWithWarning + intNextActionsTodoTxtNewWithWarning +
	intNextActionsTodoTxtExistingWithWarning + intNextActionsTodoTxtExistingNotImported + 
	intNextActionsExportedWithWarning + intProjectCorrected > 0) {
// Set the button text
	button.setText(strLogButtonText)
	button.setHorizontalAlignment(SwingConstants.LEFT)
	button.setBorderPainted(false)
	button.setOpaque(false)
	button.setBackground(Color.WHITE)
// Show the path to the Freeplane log file as a tooltip
	button.setToolTipText(uriFreeplaneLog.toString())
	button.setPreferredSize(new Dimension(105, 65))
// If you click the button, open the Freeplane log file
	button.actionPerformed = { e -> loadUri(uriFreeplaneLog) }
	container.add(button)
}
// Adjust the size of the frame to the contents
frameSyncResult.pack()
// Center the frame on the screen
frameSyncResult.setLocationRelativeTo(null)
// Display the message dialogue
frameSyncResult.setVisible(true)
