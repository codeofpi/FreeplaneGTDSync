package gtdSync

import java.text.MessageFormat
import java.text.SimpleDateFormat
import org.freeplane.core.ui.components.UITools
import org.freeplane.core.util.LogUtils
import org.freeplane.core.util.TextUtils
import org.freeplane.plugin.script.proxy.Proxy

class NextActionHandler {
// counters
	static int intNextActionsTotal = 0
	static int intNextActionsNew = 0
	static int intNextActionsNewWithWarning = 0
	static int intNextActionsExisting = 0
	static int intNextActionsExistingChanged = 0
	static int intNextActionsExistingWithWarning = 0
	static int intNextActionsExistingNotImported = 0
// Attribute and other labels, translated
	static String strNameLabel
	static String strPriorityLabel
	static String strCreatedLabel
	static String strLinkLabel
	static String strOldLabel
	static String strNewLabel
	static String strYes
	static String strNo
	static String strSettingPattern
// Text for different exceptions, translated
	static String strExceptionIntroduction
	static String strExceptionIntroductionExport
	static String strExceptionDateReplace
	static String strException1DateMissing
	static String strException2DatesMissing
	static String strExceptionDateCreatedMissing
	static String strExceptionNoSquareBracket
	static String strExceptionURL
	static String strExceptionNoHistory
	static String strExceptionURInotSupported
	static String strExceptionExport
	static String strNotImportedNotUpdatable
	static String strNotUpdatableCreated
	static String strNotUpdatableProject
// Today's date
	static Date dateToday = new Date()
// Todo.txt line
	String strTodoTxtLine = ""
// Next Action attributes
	String strName = ""
	boolean blnPriority = false
	String strPriority = ""
	Date dateDone
	String strDone = ""
	Date dateCreated
	String strCreated = ""
	String strWhere = ""
	String strWho = ""
	Date dateThreshold
	String strThreshold = ""
	Date dateWhen
	String strWhen = ""
	String strProject = ""
	URI uriLink = null
	String strLink = ""
	String strNodeID = ""
// Default historic values
	static String strUnknown
	static Date dateOld = new Date(0)
	static String strLinkOldDefault
// Historic Next Action attributes
	String strNameOld = ""
	boolean blnPriorityOld = false
	String strPriorityOld = ""
	Date dateDoneOld
	String strDoneOld = ""
	Date dateCreatedOld
	String strCreatedOld = ""
	String strWhereOld = ""
	String strWhoOld = ""
	Date dateThresholdOld = dateOld
	String strThresholdOld = ""
	Date dateWhenOld = dateOld
	String strWhenOld = ""
	String strProjectOld = ""
	URI uriLinkOld = null
	String strLinkOld = ""
// Indicators/headers for changed attributes
	String strNameChanged = ""
	String strPriorityChanged = ""
	String strDoneChanged = ""
	String strCreatedChanged = ""
	String strWhereChanged = ""
	String strWhoChanged = ""
	String strThresholdChanged = ""
	String strWhenChanged = ""
	String strProjectChanged = ""
	String strLinkChanged = ""
// Indicators when updating mind map from todo.txt
	boolean blnNotImported = false
	boolean blnChanged = false
// Storage for warning and info messages
	String strMessage = ""
	String strImportInfo = ""

	static {
		strNameLabel = TextUtils.getText("gtdSyncAttrNameLabel","Description")
		strPriorityLabel = TextUtils.getText("gtdSyncAttrPriorityLabel","Priority")
		strCreatedLabel = TextUtils.getText("gtdSyncAttrCreatedLabel","Created")
		strLinkLabel = TextUtils.getText("gtdSyncAttrLinkLabel","URL")
		strOldLabel = TextUtils.getText("gtdSyncOldLabel","Old")
		strNewLabel = TextUtils.getText("gtdSyncNewLabel","New")
		strYes = TextUtils.getText("gtdSyncYes","Yes")
		strNo = TextUtils.getText("gtdSyncNo","No")
		strSettingPattern = TextUtils.getText("gtdSyncSettingPattern","Icon:")
		strExceptionIntroduction = TextUtils.getText("gtdSyncExceptionIntroduction",
			"Processing line{1}from file {0} resulted in the following messages:")
		strExceptionIntroductionExport = TextUtils.getText("gtdSyncExceptionIntroductionExport",
			"With the export of next action {0} the following corrections took place:")
		strExceptionDateReplace = TextUtils.getText("gtdSyncExceptionDateReplace",
			"Value {0} for date {1} is invalid and has been replaced with the current date.")
		strException1DateMissing = TextUtils.getText("gtdSyncException1DateMissing",
			"For this completed action 1 of the 2 dates at the beginning of the line is missing.\
 Date {0} has been set to the present value {2}, date {1} to today.")
		strException2DatesMissing = TextUtils.getText("gtdSyncException2DatesMissing",
			"For this completed action 2 dates at the beginning of the line are missing. Date\
 {0} and date {1} have been set to today.")
		strExceptionDateCreatedMissing = TextUtils.getText("gtdSyncExceptionDateCreatedMissing",
			"For this next action a date is missing at the beginning of the line. Date {0} has\
 been set to today.")
		strExceptionNoSquareBracket = TextUtils.getText("gtdSyncExceptionNoSquareBracket",
			"For attribute {0} the closing bracket {1} cannot be found.")
		strExceptionURL = TextUtils.getText("gtdSyncExceptionURL",
			"{0} is not a valid URL and has been removed.")
		strExceptionNoHistory = TextUtils.getText("gtdSyncExceptionNoHistory",
			"Changes since last sync cannot be determined because the historic record is missing.\
 It is assumed that all attributes have changed.")
		strExceptionURInotSupported = TextUtils.getText("gtdSyncExceptionURInotSupported",
			"This next action contains the link {0}. GTD Sync does not support and export this type\
 of link.")
		strExceptionExport = TextUtils.getText("gtdSyncExceptionExport", "Node {0} caused an\
 unexpected error in the export process. The export process should proceed as expected for the\
 other nodes. Please submit a defect at https://sourceforge.net/p/gtdsync/tickets/new/.<br><br>\
Error message:<br><br>")
		strNotImportedNotUpdatable = TextUtils.getText("gtdSyncNotImportedNotUpdatable",
			"NOT IMPORTED: It is not possible to update the mind map foor the following changes:")
		strNotUpdatableCreated = TextUtils.getText("gtdSyncNotUpdatableCreated",
			"Cause: this date is never changed after the creation of a next action. Possibly this\
 has happened by accident.")
		strNotUpdatableProject = TextUtils.getText("gtdSyncNotUpdatableProject",
			"Cause: the new position of this next action in the mind map cannot be derived\
 automatically. Because of this you have to reposition this next action manually.")
		strUnknown = TextUtils.getText("gtdSyncUnknown", "Unknown")
		strLinkOldDefault = "http://" + TextUtils.getText("gtdSyncLinkOldDefault", "www.unknown.com")
	}

// Constructor for parsing a todo.txt line
	NextActionHandler(String strTextLine) {
		def lstDelimiters = []
// Delimiters for parsing several attributes
		lstDelimiters += [label:"where", start:"@", stop:" ", strip:1]
		lstDelimiters += [label:"who", start:"[", stop:"]", strip:1]
		lstDelimiters += [label:"threshold", start:"t:", stop:" ", strip:2]
		lstDelimiters += [label:"when", start:"due:", stop:" ", strip:4]
		lstDelimiters += [label:"project", start:"+", stop:" ", strip:1]
		lstDelimiters += [label:"url", start:"http://", stop:" ", strip:0]
		lstDelimiters += [label:"url", start:"https://", stop:" ", strip:0]
		lstDelimiters += [label:"outlook", start:"outlook:", stop:" ", strip:0]
		lstDelimiters += [label:"nodeID", start:"node:ID_", stop:" ", strip:5]
// Start and end position of an attribute
		int intStartPosition
		int intStopPosition
// String for storing original value for context when it needs to be changed to waiting-for
		String strWhereOld
// Store todo.txt line twice, second one is used to strip attributes until action name remains.
		strTodoTxtLine = strTextLine.trim()
		strName = strTextLine.trim()
// Handle dates created and done for a completed action.
		if (strName =~ /^x\s/) {
			strName = stripAttr(strName, 0, 1)
// Both dates are specified
			if (strName =~ /^2\d{3}-\d{2}-\d{2}\s+2\d{3}-\d{2}-\d{2}\s/) {
				dateDone = parseDate(strName.substring(0, 10), Support.strDoneLabel)
				strName = stripAttr(strName, 0, 10)
				dateCreated = parseDate(strName.substring(0, 10), strCreatedLabel)
				strName = stripAttr(strName, 0, 10)
			}
			else {
// Only one date is specified
				if (strName =~ /^2\d{3}-\d{2}-\d{2}\s/) {
					strMessage += "\n- " + MessageFormat.format(strException1DateMissing,
						Support.quote(strCreatedLabel), Support.quote(Support.strDoneLabel),
						Support.quote(strName.substring(0, 10)))
					dateDone = dateToday
					dateCreated = parseDate(strName.substring(0, 10), strCreatedLabel)
					strName = stripAttr(strName, 0, 10)
				}
// None of the 2 dates is specified
				else {
					strMessage += "\n- " + MessageFormat.format(strException2DatesMissing,
						Support.quote(strCreatedLabel), Support.quote(Support.strDoneLabel))
					dateDone = dateToday
					dateCreated = dateToday
				}
			}
		}
// Handle priority and date created for a next action
		else {
// A priority is specified
			if (strName =~ /^\([A-Z]\)\s/) {
				blnPriority = true
				strName = stripAttr(strName, 0, 3)
			}
// Date created is specified
			if (strName =~ /^2\d{3}-\d{2}-\d{2}\s/) {
				dateCreated = parseDate(strName.substring(0, 10), strCreatedLabel)
				strName = stripAttr(strName, 0, 10)
			}
// Date created is not specified
			else {
				strMessage += "\n- " + MessageFormat.format(strExceptionDateCreatedMissing,
					Support.quote(strCreatedLabel))
				dateCreated = dateToday
			}
		}
// Parse the other attributes
		lstDelimiters.each {
// Add a space, so attributes at the end of the line will be parsed.
			strName = strName + " "
// Determine start position of attribute
			intStartPosition = strName.indexOf(it["start"])
// If start position is found continue
			if (intStartPosition > -1) {
// Determine end position of attribute
				intStopPosition = strName.indexOf(it["stop"], intStartPosition)
				switch( it["label"]) {
// Parsing of context
					case "where":
						strWhere = strName.substring(intStartPosition + it["strip"],
							intStopPosition)
						break
// Parsing of action owner and related corrections of context
					case "who":
// This is the only attribute for which stop delimiter (']' instead of ' ') could be missing
						if (intStopPosition == -1) {
							strMessage += "\n- " + MessageFormat.format(
								strExceptionNoSquareBracket, Support.quote(Support.strWhoLabel),
								Support.quote(it["stop"]))
						}
						else {
							strWho = strName.substring(intStartPosition + it["strip"],
								intStopPosition)
// For removing action owner from action name later 1 extra character has to be removed
							intStopPosition = intStopPosition + 1
						}
						break
// Parsing of threshold date
					case "threshold":
						dateThreshold = parseDate(strName.substring(intStartPosition + 
							it["strip"], intStopPosition), Support.strThresholdLabel)
						if (dateThreshold) {}
// If the threshold date could not be parsed, do not strip the value from the name.
						else {
							if (intStopPosition - intStartPosition > it["strip"]) {
								intStopPosition = -1
							}
						}
						break
// Parsing of when date
					case "when":
						dateWhen = parseDate(strName.substring(intStartPosition + 
							it["strip"], intStopPosition), Support.strWhenLabel)
						if (dateWhen) {}
// If the when date could not be parsed, do not strip the value from the name.
						else {
							if (intStopPosition - intStartPosition > it["strip"]) {
								intStopPosition = -1
							}
						}
						break
// Parsing of the project name
					case "project":
						strProject = strName.substring(intStartPosition + it["strip"],
							intStopPosition)
						break
// Parsing of URL
					case "url":
						try {
							uriLink = strName.substring(intStartPosition + it["strip"],
								intStopPosition).toURI()
						}
// If it is not a valid URL, log a message
						catch(e) {
							strMessage += "\n- " + MessageFormat.format(strExceptionURL,
								Support.quote(strName.substring(intStartPosition + it["strip"],
								intStopPosition)))
						}
						break
// Parsing of the node ID, technical key for matching existing next actions in the GTD mind map.
					case "nodeID":
						strNodeID = strName.substring(intStartPosition + it["strip"],
							intStopPosition)
						break
// Any labels not processed are reported here.
					default:
						LogUtils.info("'" + it["label"] + "' is not covered by a case.\n")
				}
// Remove the parsed attribute from the next action name.
// Also the 'outlook:' key and invalid URLs are removed.
				if (intStopPosition > intStartPosition) {
					strName = stripAttr(strName, intStartPosition, intStopPosition)
				}
			}
// Remove the previously added trailing space from next action name
			strName = strName.trim()
		}
// Check for and handle exceptions for attribute strWhere, see function Support.strWhereFinal
		strWhere = Support.strWhereFinal(strWhere, strWho)
		strMessage = addExceptionMessage(strMessage)
	}

// Constructor for assembling a todo.txt line from a Next Action node.
	NextActionHandler(Proxy.Node nodeCurrent, nodesProjects, String strIconToday,
		String strExceptionMessage) {
		def icons = nodeCurrent.icons.icons
		strMessage = strExceptionMessage
// Check for Today icon and if present add priority to todo.txt line
		icons.each {
			if (it == strIconToday) {
				blnPriority = true
			}
		}
		if (blnPriority) {
			strTodoTxtLine = "(A) "
		}
// Add the date created to the todo.txt line
		dateCreated = nodeCurrent.createdAt
		strTodoTxtLine += Support.dateFormat.format(dateCreated) + " "
// Add the description of the next action to the todo.txt line
		strName = nodeCurrent.plainText.trim()
		strTodoTxtLine += strName + " "
// Add the context to the todo.txt line
		strWhere = nodeCurrent[(Support.strWhereLabel)].toString().trim()
		strTodoTxtLine += "@" + strWhere + " "
// If the action is delegated, add the owner to the todo.txt line.
		strWho = nodeCurrent[(Support.strWhoLabel)].toString().trim()
		if (strWho) { strTodoTxtLine += "[" + strWho + "] " }
// Add the threshold date to the todo.txt line, if any
		if (nodeCurrent[(Support.strThresholdLabel)]) {
			strThreshold = Support.dateFormat.format(nodeCurrent[(Support.strThresholdLabel)].date)
			strTodoTxtLine += "t:" + strThreshold + " "
		}
// Add the due date to the todo.txt line, if any
		if (nodeCurrent[(Support.strWhenLabel)]) {
			strWhen = Support.dateFormat.format(nodeCurrent[(Support.strWhenLabel)].date)
			strTodoTxtLine += "due:" + strWhen + " "
		}
// Lookup if the next action is a descendant of a project and, if so, add the project name
// to the todo.txt line
		nodesProjects.each {
			if ((it) && nodeCurrent.isDescendantOf(it)){
				strProject = it.plainText.trim()
			}
		}
		if (strProject) { strTodoTxtLine += "+" + strProject + " " }
// Check if there is a link and whether it is supported. If so, add it to the todo.txt line.
		strLink = nodeCurrent.link.uri.toString().trim()
		switch (strLink) {
			case ~/http:.*/:
				break
			case ~/https:.*/:
				break
			case ~/outlook:.*/:
				strLink = "outlook:"
				break
			case "null":
				strLink = ""
				break
			default:
				strMessage += "\n- " + MessageFormat.format(strExceptionURInotSupported,
					Support.quote(strLink))
				strLink = ""
		}
		if (strLink) { strTodoTxtLine += strLink + " " }
// Add the node ID to the todo.txt line
		strNodeID = nodeCurrent.id
		strTodoTxtLine += "node:" + strNodeID
// Log messages and count exported next actions and corrected next actions
		intNextActionsExisting++
		if (strMessage) {
			strMessage = MessageFormat.format(strExceptionIntroductionExport,
				Support.quote(strName)) + strMessage + "\n"
			LogUtils.info(strMessage)
			intNextActionsExistingWithWarning++
		}
	}

// This function writes the warnings to log and counts the number of lines processed
	void logAndCounters(String strFileName) {
// Log the collected warning messages, if any
		if (strMessage + strImportInfo) {
			strImportInfo = MessageFormat.format(strExceptionIntroduction,
				Support.quote(strFileName), "\n" + Support.quote(strTodoTxtLine) + "\n") +
				strMessage + strImportInfo + "\n"
			LogUtils.info(strImportInfo)
		}
// Add 1 to the necessary counters
		intNextActionsTotal++
		if (strNodeID) {
			intNextActionsExisting++
			if (blnChanged) {
				intNextActionsExistingChanged++
			}
			if (strMessage){
				if (blnNotImported) {
					intNextActionsExistingNotImported++
				}
				else {
					intNextActionsExistingWithWarning++
				}
			}
		}
		else {
			intNextActionsNew++
			if (strMessage){
				intNextActionsNewWithWarning++
			}
		}
	}
	
// This function warns dat the historic record for an existing next action is missing and adds
// default historic values for existing next actions in a way that all attributes (except 
// dateCreated) are changed.
	void defaultHistoricValues() {
		strMessage += "\n- " + strExceptionNoHistory
		strNameOld = strUnknown
		if (!(blnPriority)) { blnPriorityOld = true }
		dateCreatedOld = dateCreated
		strWhereOld = strUnknown
		strWhoOld = strUnknown
		strProjectOld = strUnknown
		if (uriLink) { uriLinkOld = strLinkOldDefault.toURI() }
	}

// This functions lists all changes imported or not imported.
	void listChangedAttributes() {
		if (strNameChanged) {
			strImportInfo += strNameChanged
			strImportInfo += "\n" + strOldLabel + ": " + strNameOld
			strImportInfo += "\n" + strNewLabel + ": " + strName
		}
		if (strPriorityChanged) {
			if (blnPriorityOld) {
				strPriorityOld = strYes
				strPriority = strNo
			}
			else {
				strPriorityOld = strNo
				strPriority = strYes
			}
			strImportInfo += strPriorityChanged
			strImportInfo += "\n" + strOldLabel + ": " + strPriorityOld
			strImportInfo += "\n" + strNewLabel + ": " + strPriority
		}
		if (strDoneChanged) {
			if (dateDoneOld) { strDoneOld = Support.dateFormat.format(dateDoneOld) }
			if (dateDone) { strDone = Support.dateFormat.format(dateDone) }
			strImportInfo += strDoneChanged
			strImportInfo += "\n" + strOldLabel + ": " + strDoneOld
			strImportInfo += "\n" + strNewLabel + ": " + strDone
		}
		if (strCreatedChanged && !(blnNotImported)) {
			if (dateCreatedOld) { strCreatedOld = Support.dateFormat.format(dateCreatedOld) }
			if (dateCreated) { strCreated = Support.dateFormat.format(dateCreated) }
			strImportInfo += strCreatedChanged
			strImportInfo += "\n" + strOldLabel + ": " + strCreatedOld
			strImportInfo += "\n" + strNewLabel + ": " + strCreated
		}
		if (strWhereChanged) {
			strImportInfo += strWhereChanged
			strImportInfo += "\n" + strOldLabel + ": " + strWhereOld
			strImportInfo += "\n" + strNewLabel + ": " + strWhere
		}
		if (strWhoChanged) {
			strImportInfo += strWhoChanged
			strImportInfo += "\n" + strOldLabel + ": " + strWhoOld
			strImportInfo += "\n" + strNewLabel + ": " + strWho
		}
		if (strThresholdChanged) {
			if (dateThresholdOld) { strThresholdOld = Support.dateFormat.format(dateThresholdOld) }
			if (dateThreshold) { strThreshold = Support.dateFormat.format(dateThreshold) }
			strImportInfo += strThresholdChanged
			strImportInfo += "\n" + strOldLabel + ": " + strThresholdOld
			strImportInfo += "\n" + strNewLabel + ": " + strThreshold
		}
		if (strWhenChanged) {
			if (dateWhenOld) { strWhenOld = Support.dateFormat.format(dateWhenOld) }
			if (dateWhen) { strWhen = Support.dateFormat.format(dateWhen) }
			strImportInfo += strWhenChanged
			strImportInfo += "\n" + strOldLabel + ": " + strWhenOld
			strImportInfo += "\n" + strNewLabel + ": " + strWhen
		}
		if (strProjectChanged && !(blnNotImported)) {
			strImportInfo += strProjectChanged
			strImportInfo += "\n" + strOldLabel + ": " + strProjectOld
			strImportInfo += "\n" + strNewLabel + ": " + strProject
		}
		if (strLinkChanged) {
			if (uriLinkOld) { strLinkOld = uriLinkOld.toString() }
			if (uriLink) { strLink = uriLink.toString() }
			strImportInfo += strLinkChanged
			strImportInfo += "\n" + strOldLabel + ": " + strLinkOld
			strImportInfo += "\n" + strNewLabel + ": " + strLink
		}
	}

// This functions warns for and lists not updatable changes
	void notUpdatableChanges() {
		strMessage += "\n- " + strNotImportedNotUpdatable
		if (strCreatedChanged) {
			if (dateCreatedOld) { strCreatedOld = Support.dateFormat.format(dateCreatedOld) }
			if (dateCreated) { strCreated = Support.dateFormat.format(dateCreated) }
			strMessage += strCreatedChanged
			strMessage += "\n" + strOldLabel + ": " + strCreatedOld
			strMessage += "\n" + strNewLabel + ": " + strCreated
			strMessage += "\n" + strNotUpdatableCreated
		}
		if (strProjectChanged) {
			strMessage += strProjectChanged
			strMessage += "\n" + strOldLabel + ": " + strProjectOld
			strMessage += "\n" + strNewLabel + ": " + strProject
			strMessage += "\n" + strNotUpdatableProject
		}
		blnNotImported = true
	}
	
// recursive unfolding of branch
	static void unfoldBranch(Proxy.Node nodeCurrent){
		Proxy.Node nodeRoot = nodeCurrent.getMap().getRoot()
		if (nodeCurrent != nodeRoot){
			nodeCurrent.setFolded(false)
			unfoldBranch(nodeCurrent.getParent())
		}
	}

//Create "new" or project node if not present
	static def checkNode(Proxy.Node nodeCurrent, Proxy.Node nodeParent, String strNodeText, String strIconText) {
	    if (nodeCurrent == null) {
	        nodeParent.children.each {
	            if (it.text == strNodeText) {nodeCurrent = it}
	        }
	    }
	    if (nodeCurrent == null) {
	        nodeCurrent = nodeParent.createChild(strNodeText)
	        if (strIconText){nodeCurrent.icons.add(strIconText)}
	    }
	    return nodeCurrent
	}

// recursive walk through nodes to find Projects
	static def findProjects(Proxy.Node nodeCurrent, String strIconProject){
		def icon = nodeCurrent.icons.icons
		def nodesProject = []
	
// include nodesProject if it has project icon and its not the icon setting node for projects
		if (icon[0] == strIconProject){
			if (!(nodeCurrent.text =~ strSettingPattern)){
//				intNextActionsNew++
// If the project name contains spaces, the words will be capitalised and the spaces will be
// removed.
				if (nodeCurrent.text =~ /\s/) {
					nodeCurrent.text = Support.removeWhitespace(nodeCurrent.text,
						Support.strProjectLabel)
					LogUtils.info("- " + Support.strExceptionMessage + "\n")
					Support.strExceptionMessage = ""
					intNextActionsNewWithWarning++
				}
				nodesProject = [nodeCurrent]
			}
		}
	
		nodeCurrent.children.each {
			nodesProject += findProjects(it, strIconProject)
		}
	
		return nodesProject
	}

// Recursive walk through nodes to find Next Actions
	static def findNextActions(Proxy.Node nodeCurrent, nodesProjects, String strIconNextAction,
		String strIconToday){
		def nahsExport = []
		def icons = nodeCurrent.icons.icons
		boolean blnIconNextAction = false
		Date dateThreshold
		Date dateWhen
		String strMessage = ""
// check for Next Action icon
		icons.each {
			if (it == strIconNextAction){
				blnIconNextAction = true
			}
		}
		if (blnIconNextAction) {
			if (!(nodeCurrent.text =~ strSettingPattern)){
// Check and correct Next Action attributes on node
				dateThreshold = Support.textToDate(nodeCurrent[(Support.strThresholdLabel)].toString(),
					Support.strThresholdLabel)
				strMessage = addExceptionMessage(strMessage)
				dateWhen = Support.textToDate(nodeCurrent[(Support.strWhenLabel)].toString(),
					Support.strWhenLabel)
				strMessage = addExceptionMessage(strMessage)
				Support.NextActionAttributes(nodeCurrent,
					nodeCurrent[(Support.strWhereLabel)].toString(),
					nodeCurrent[(Support.strWhoLabel)].toString(), dateThreshold, dateWhen, null)
				strMessage = addExceptionMessage(strMessage)
				nahsExport = new NextActionHandler(nodeCurrent, nodesProjects, strIconToday,
					strMessage)
			}
		}
		else {
			nodeCurrent.children.each {
				try {
					nahsExport += findNextActions(it, nodesProjects, strIconNextAction, strIconToday)
				}
				catch (e) {
					strMessage = "<html><body><div style='width: 500px'>" +
						MessageFormat.format(strExceptionExport, Support.quote(it.text)) +
						e.toString()
					UITools.errorMessage(strMessage)
				}
			}
		}
		return nahsExport
	}

// Add exception messages for logging in freeplane file
	private static String addExceptionMessage(String strMessage) {
		if (Support.strExceptionMessage) {
			strMessage += "\n- " + Support.strExceptionMessage
			Support.strExceptionMessage = ""
		}
		return strMessage
	}

// Remove an attribute from the todo.txt line
	private static String stripAttr(String strTodoLine, int intPosFirst, int intPosSecond) {
		String strFirstPart = strTodoLine.substring(0, intPosFirst)
		strFirstPart = strFirstPart.trim()
		String strSecondPart = strTodoLine.substring(intPosSecond)
		strSecondPart = strSecondPart.trim()
		if (strFirstPart=="") {strTodoLine = strSecondPart}
		else {
			if (strSecondPart=="") {strTodoLine = strFirstPart}
			else {strTodoLine = strFirstPart + " " + strSecondPart}
		}
		return strTodoLine
	}

// Parse date and generate error message when necessary
	private Date parseDate(String strDate, String strAttribute) {
		Date dateParsed = Support.textToDate(strDate, strAttribute)
		if (Support.strExceptionMessage) {
			switch(strAttribute) {
				case [Support.strDoneLabel, strCreatedLabel]:
					dateParsed = dateToday
					strMessage += "\n- " + MessageFormat.format(strExceptionDateReplace,
						Support.quote(strDate), Support.quote(strAttribute))
					Support.strExceptionMessage = ""
					break
				case [Support.strThresholdLabel, Support.strWhenLabel]:
					strMessage += "\n- " + Support.strExceptionMessage
					Support.strExceptionMessage = ""
					break
				default:
					Support.strExceptionMessage = ""
			}
		}
		return dateParsed
	}
}
