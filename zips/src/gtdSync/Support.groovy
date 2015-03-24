package gtdSync

import java.text.MessageFormat
import java.text.SimpleDateFormat
import org.apache.commons.lang.WordUtils
import org.freeplane.core.ui.components.UITools
import org.freeplane.core.util.TextUtils
import org.freeplane.features.attribute.NodeAttributeTableModel
import org.freeplane.plugin.script.proxy.Proxy

class Support {
// Default icons
	static String strIconNextAction = "yes"
	static String strIconProject = "list"
	static String strIconToday = "bookmark"
	static String strIconDone = "button_ok"
// Names of nodes with custom icons, translated
	static String strNodeNextActionIcon
	static String strNodeProjectIcon
	static String strNodeTodayIcon
	static String strNodeDoneIcon
// Attribute labels, translated
	static String strWhereLabel
	static String strWhoLabel
	static String strThresholdLabel
	static String strWhenLabel
	static String strDoneLabel
	static String strProjectLabel
// Text for different exceptions, translated
	static String strExceptionInfoDate
	static String strExceptionWhitespace
	static String strExceptionDelegated
	static String strExceptionNoContext
// Translation for _anywhere list, waiting-for list and null values
	static String strAnywhere
	static String strWaitingFor
	static String strEmpty
// Property to collect all exceptions to be displayed in log or info message
	static String strExceptionMessage = ""
// Definition of standard date format
	static String strDateFormat = "yyyy-MM-dd"
	static def dateFormat = new SimpleDateFormat(strDateFormat)
// Translation currently used
	static String strGTDSyncLanguage
	
// Initialise translations
	static {
		strWhereLabel = TextUtils.getText("gtdSyncAttrWhereLabel","Where")
		strWhoLabel = TextUtils.getText("gtdSyncAttrWhoLabel","Who")
		strThresholdLabel = TextUtils.getText("gtdSyncAttrThresholdLabel","Threshold")
		strWhenLabel = TextUtils.getText("gtdSyncAttrWhenLabel","When")
		strDoneLabel = TextUtils.getText("gtdSyncAttrDoneLabel","Done")
		strProjectLabel = TextUtils.getText("gtdSyncAttrProjectLabel","Project")
		strExceptionInfoDate = TextUtils.getText("gtdSyncExceptionInfoDate",
			"Attribute {0} contained invalid date {1}. Enter the correct date in format {2}.")
		strExceptionWhitespace = TextUtils.getText("gtdSyncExceptionWhitespace",
			"The whitespace in value {0} for attribute {1} has been removed.")
		strExceptionDelegated = TextUtils.getText("gtdSyncExceptionDelegated", "Since this next\
 action is delegated, the value for attribute {0} has been changed from {1} to {2}.")
		strExceptionNoContext = TextUtils.getText("gtdSyncExceptionNoContext", "Since this next\
 action is not delegated, the value for attribute {0} has been changed from {2} to {1}.")
		dateFormat.lenient = false
		strWaitingFor = TextUtils.getText('gtdSyncWaitingFor', 'Waiting-for')
		strEmpty = TextUtils.getText('gtdSyncEmpty', 'empty')
		strAnywhere = TextUtils.getText("gtdSyncAnywhere","_anywhere")
		strGTDSyncLanguage = TextUtils.getText("gtdSyncLanguage","en")
	}

// Constructor if no parameters are passed, nothing extra has to be done
	Support() { }

// Constructor if nodeRoot is passed, determine custom icon if any
	Support(Proxy.Node nodeRoot) {
		strNodeNextActionIcon = TextUtils.getText("gtdSyncNodeNextActionIcon",
			"Icon: Next action")
		strIconNextAction = findIconKey(nodeRoot,strNodeNextActionIcon,strIconNextAction)
		strNodeProjectIcon = TextUtils.getText("gtdSyncNodeProjectIcon","Icon: Project")
		strIconProject = findIconKey(nodeRoot,strNodeProjectIcon,strIconProject)
		strNodeTodayIcon = TextUtils.getText("gtdSyncNodeTodayIcon","Icon: Today")
		strIconToday = findIconKey(nodeRoot,strNodeTodayIcon,strIconToday)
		strNodeDoneIcon = TextUtils.getText("gtdSyncNodeDoneIcon","Icon: Today")
		strIconDone = findIconKey(nodeRoot,strNodeDoneIcon,strIconDone)
	}

// Add or update attributes of next action node
	static void NextActionAttributes(Proxy.Node thisNode, String strWhere, String strWho,
		Date dateThreshold, Date dateWhen, Date dateDone) {
// Map for node attributes
		def nodeAttr = [:]
// Objects necessary for changing the column width of the attribute table
		NodeAttributeTableModel attributeTableModel
		int intCurrentWidth
// If the parameter is empty, all attributes except 'Done' and 'Where' will be added with an empty string
// Handling 3 exceptions for Where attribute: See function strWhereFinal
		nodeAttr[(strWhereLabel)] = strWhereFinal(strWhere, strWho)
// Adding/updating the Who attribute.
		if (strWho) {nodeAttr[(strWhoLabel)] = strWho} else {nodeAttr[(strWhoLabel)] = ""}
// Adding/updating the Threshold attribute.
		if (dateThreshold) {
			nodeAttr[(strThresholdLabel)] = dateFormat.format(dateThreshold)
		}
		else {nodeAttr[(strThresholdLabel)] = ""}
// Adding/updating the When attribute.
		if (dateWhen) {nodeAttr[(strWhenLabel)] = dateFormat.format(dateWhen)}
		else {nodeAttr[(strWhenLabel)] = ""}
// Adding/updating the Done attribute.
		if (dateDone) {nodeAttr[(strDoneLabel)] = dateFormat.format(dateDone)}
// Writing the attribute table
		thisNode.attributes = nodeAttr
// If the value column of the attribute table is too narrow, make it wider
		attributeTableModel = thisNode.attributes.getNodeAttributeTableModel()
		intCurrentWidth = attributeTableModel.getColumnWidth(1)
		if (intCurrentWidth < 125) {
			attributeTableModel.layout.setColumnWidth(1,125)
		}
	}

// Remove all icons from a node and than add any number of given icons
	static void ReplaceIcons(Proxy.Node nodeCurrent, String[] strIcons) {
		while (nodeCurrent.icons.remove(0)) {
			nodeCurrent.icons.remove(0)
		}
		strIcons.each { nodeCurrent.icons.add(it) }
	}

// Parse text to a date and return null and exception message when text is not a valid date.
	static Date textToDate(String strDate, String strAttribute) {
		Date dateAttribute = null
		if (strDate) {
			try {
				dateAttribute = dateFormat.parse(strDate)
			}catch(e){
				if (strExceptionMessage) { strExceptionMessage += "\n" }
				strExceptionMessage += MessageFormat.format(strExceptionInfoDate,
					quote(strAttribute), quote(strDate), quote(strDateFormat))
			}
		}
		return dateAttribute
	}

// Function to handle 3 exceptions for Where attribute:
// 1: If Who attribute has a value it should be changed to Waiting-For
// 2: If Who attribute is empty and Where attribute is empty or Waiting-For it
//    should be changed to _anywhere
// 3: If it contains spaces, the words will be capitalised and the spaces will be removed
	static String strWhereFinal(String strWhere, String strWho) {
// String for storing old Where value in case it is changed to Waiting-for
		String strWhereOld
		if (strWho) {
// Exception 1
			if (strWhere != strWaitingFor) {
				if (strWhere) {
					strWhereOld = quote(strWhere)
				}
				else {
					strWhereOld = strEmpty
				}
				if (strExceptionMessage) { strExceptionMessage += "\n" }
				strExceptionMessage += MessageFormat.format(strExceptionDelegated,
					quote(strWhereLabel), strWhereOld, quote(strWaitingFor))
				strWhere = strWaitingFor
			}
		}
		else {
			if (strWhere) {
// Exception 2
				if (strWhere == strWaitingFor) {
					if (strExceptionMessage) { strExceptionMessage += "\n" }
					strExceptionMessage += MessageFormat.format(strExceptionNoContext,
						quote(strWhereLabel), quote(strAnywhere), quote(strWaitingFor))
					strWhere = strAnywhere
				}
				else {
// Exception 3
					if (strWhere =~ /\s/) {
						strWhere = removeWhitespace(strWhere, strWhereLabel)
					}
				}
			}
			else {
// Exception 2
				if (strExceptionMessage) { strExceptionMessage += "\n" }
				strExceptionMessage += MessageFormat.format(strExceptionNoContext,
					quote(strWhereLabel), quote(strAnywhere), strEmpty)
				strWhere = strAnywhere
			}
		}
		return strWhere
	}

// Capitalise words and remove spaces from Where attribute or project
	static String removeWhitespace(String strWithWhitespace, String strAttribute) {
		if (strExceptionMessage) { strExceptionMessage += "\n" }
		strExceptionMessage += MessageFormat.format(strExceptionWhitespace,
			quote(strWithWhitespace), quote(strAttribute))
		strWithWhitespace = WordUtils.capitalizeFully(strWithWhitespace)
		strWithWhitespace = strWithWhitespace.replaceAll("\\s+","")
		return strWithWhitespace
	}
// Wrap string in quotes as part of the string
	static String quote(String strQuote) {
		strQuote = "'" + strQuote + "'"
		return strQuote
	}

// Get icon key names from Settings/Icons nodes
	static String findIconKey(Proxy.Node nodeCurrent, String nodeLabel, String iconLast){
		def icons = nodeCurrent.icons.icons
		String nodeText = nodeCurrent.text
		String iconFound = iconLast
	
		if (nodeText.trim() == nodeLabel){
			iconFound = icons[0]
		}
	
		nodeCurrent.children.each {
			iconFound = findIconKey(it, nodeLabel, iconFound)
		}
		return iconFound
	}

// Check whether language differs and, if so, recommend what to do, see user story 5
// https://sourceforge.net/p/gtdsync/tickets/5
	static boolean blnDifferenceInLanguage(Proxy.Node nodeRoot) {
// By default there is no difference and the requesting script will continue
		boolean blnDifference = false
// Translation language previously used in GTD mind map
		String strGTDMindMapLanguage = nodeRoot['gtdMindMapLanguage'].toString()
		if (strGTDMindMapLanguage) {
// Acceptance criterion 2: no difference, no message, script continues
			if (strGTDMindMapLanguage == strGTDSyncLanguage) {}
// Acceptance criterion 3: different, message: convert map with script or change Freeplane language
// Script stops
			else {
				blnDifference = true
				String strLanguageDifference = TextUtils.getText("gtdSyncLanguageDifference",
					"The current GTD Sync language is English while {0} has been used for your\
 GTD mind map. You should change the language in the Freeplane settings to {0} and restart\
 Freeplane or convert the GTD mind map to English: from the menu choose: Tools > GTD Sync with\
 todo.txt > Convert GTD mind map to English.")
				String strLanguageUnknown = TextUtils.getText("gtdSyncLanguageUnkown",
					"an unknown language")
				String strLanguage = TextUtils.getText("OptionPanel." + strGTDMindMapLanguage,
					strLanguageUnknown)
				strLanguageDifference = '<html><body><div style="width: 400px">'+
					MessageFormat.format(strLanguageDifference, strLanguage) +
					"</div></body></html>"
				UITools.informationMessage(UITools.frame, strLanguageDifference)
			}
		}
// Acceptance criterion 4: no language indication in mind map, update map, no message, 
// script continues.
		else {
			nodeRoot['gtdMindMapLanguage'] = strGTDSyncLanguage
		}
		return blnDifference
	}
}
