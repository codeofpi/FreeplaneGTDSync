// @ExecutionModes({ON_SINGLE_NODE})
//==============================================================================
//  Convert Language in GTD mind map for GTD Sync
//
//  If you have switched translation in GTD Sync, e.g. because a new translation
//  is available, the attribute labels, the standard list names and the node
//  names for custom icons will have the wrong translation in your GTD mind map.
//  This script converts the attribute labels of next and completed actions and
//  the standard two lists for the where (a.k.a. context) attribute to the
//  correct language, preserving the date last modified of the node. It also
//  converts the node names for the custom icons to the translation in the right
//  language. By converting the language while preserving the date last modified
//  the GTD mind map is ready to be used with the changed translation of GTD
//  Sync.

import gtdSync.Support
import org.freeplane.main.addons.AddOnsController
import org.freeplane.plugin.script.proxy.Proxy

// Root node of GTD mind map
Proxy.Node nodeRoot = node.map.root
// Collect functions, translations and next action icon from support class
Support s = new Support(nodeRoot)
// Translation language previously used in GTD mind map
String strGTDMindMapLanguage = nodeRoot['gtdMindMapLanguage'].toString()

if (strGTDMindMapLanguage) {
	if (strGTDMindMapLanguage == s.strGTDSyncLanguage) {
		ui.informationMessage(ui.frame, textUtils.getText("gtdSyncConvertMapNoNeed",
			"There is no need to convert this GTD mind map because it is already using English."))
	}
	else {
// Determine presence and position of GTD Sync in list of add-ons
		int intIndexAddon = AddOnsController.controller.installedAddOns.name.indexOf("gtdSyncWithTodoTxt")
		if (intIndexAddon == -1) {
			ui.informationMessage(ui.frame, textUtils.getText("gtdSyncConvertMapNotInstalled",
				"The add-on 'GTD Sync with todo.txt' is not installed."))
			return
		}
// Get properties for language code in GTD mind map, if available
		def mapOriginalProperties = 
			AddOnsController.controller.installedAddOns.get(intIndexAddon).translations[(strGTDMindMapLanguage)]
		if (!(mapOriginalProperties)) {
			ui.informationMessage(ui.frame, '<html><body><div style="width: 400px">' +
				textUtils.getText("gtdSyncConvertMapNoOriginalLC", "The language code in the\
 root node of the GTD mind  map is not available for add-on 'GTD Sync with todo.txt'. This\
 could be caused by the fact that the language code in the root node of the GTD mind map has\
 been changed manually or that an older version of 'GTD Sync with todo.txt' is installed.") +
				"</div></body></html>")
			return
		}
// Names of nodes with custom icons, original translation
		String strNodeNextActionIconOriginal = mapOriginalProperties["gtdSyncNodeNextActionIcon"]
		String strNodeProjectIconOriginal = mapOriginalProperties["gtdSyncNodeProjectIcon"]
		String strNodeTodayIconOriginal = mapOriginalProperties["gtdSyncNodeTodayIcon"]
		String strNodeDoneIconOriginal = mapOriginalProperties["gtdSyncNodeDoneIcon"]
		String strSettingPatternOriginal = mapOriginalProperties["gtdSyncSettingPattern"]
		String strNewLabelOriginal = mapOriginalProperties["gtdSyncNewLabel"]
		String strNewLabel = textUtils.getText("gtdSyncNewLabel","New")
// Attribute labels, original translation
		String strWhereLabelOriginal = mapOriginalProperties["gtdSyncAttrWhereLabel"]
		String strWhoLabelOriginal = mapOriginalProperties["gtdSyncAttrWhoLabel"]
		String strThresholdLabelOriginal = mapOriginalProperties["gtdSyncAttrThresholdLabel"]
		String strWhenLabelOriginal = mapOriginalProperties["gtdSyncAttrWhenLabel"]
		String strDoneLabelOriginal = mapOriginalProperties["gtdSyncAttrDoneLabel"]
// Standard lists, original translation
		String strAnywhereOriginal = mapOriginalProperties["gtdSyncAnywhere"]
		String strWaitingForOriginal = mapOriginalProperties["gtdSyncWaitingFor"]
		
// Map for converted node attributes
		def nodeAttr = [:]
// List of all nodes in the GTD mind map
		List listAllNodes = c.findAll()
// Variable for preserving date last modified
		Date dateLastModified
// Convert node for each node that needs it.
		listAllNodes.each {
			if (it.text =~ strSettingPatternOriginal || it.text == strNewLabelOriginal) {
				if (it.text == strNodeNextActionIconOriginal) {
					it.text = s.strNodeNextActionIcon
				}
				if (it.text == strNodeProjectIconOriginal) {
					it.text = s.strNodeProjectIcon
				}
				if (it.text == strNodeTodayIconOriginal) {
					it.text = s.strNodeTodayIcon
				}
				if (it.text == strNodeDoneIconOriginal) {
					it.text = s.strNodeDoneIcon
				}
				if (it.text == strNewLabelOriginal) {
					it.text = strNewLabel
				}
			}
			else {
				if (it.attributes.map.containsKey((strWhereLabelOriginal))) {
					nodeAttr[(s.strWhereLabel)] = it[(strWhereLabelOriginal)]
					if (it[(strWhereLabelOriginal)] == strAnywhereOriginal) {
						nodeAttr[(s.strWhereLabel)] = s.strAnywhere
					}
					if (it[(strWhereLabelOriginal)] == strWaitingForOriginal) {
						nodeAttr[(s.strWhereLabel)] = s.strWaitingFor
					}
				}
				if (it.attributes.map.containsKey((strWhoLabelOriginal))) {
					nodeAttr[(s.strWhoLabel)] = it[(strWhoLabelOriginal)]
				}
				if (it.attributes.map.containsKey((strThresholdLabelOriginal))) {
					if (it[(strThresholdLabelOriginal)] instanceof
						org.freeplane.plugin.script.proxy.ConvertibleDate) {
						nodeAttr[(s.strThresholdLabel)] = it[(strThresholdLabelOriginal)].date
					}
					else {
						nodeAttr[(s.strThresholdLabel)] = it[(strThresholdLabelOriginal)]
					}
				}
				if (it.attributes.map.containsKey((strWhenLabelOriginal))) {
					if (it[(strWhenLabelOriginal)] instanceof
						org.freeplane.plugin.script.proxy.ConvertibleDate) {
						nodeAttr[(s.strWhenLabel)] = it[(strWhenLabelOriginal)].date
					}
					else {
						nodeAttr[(s.strWhenLabel)] = it[(strWhenLabelOriginal)]
					}
				}
				if (it.attributes.map.containsKey((strDoneLabelOriginal))) {
					if (it[(strDoneLabelOriginal)] instanceof
						org.freeplane.plugin.script.proxy.ConvertibleDate) {
						nodeAttr[(s.strDoneLabel)] = it[(strDoneLabelOriginal)].date
					}
					else {
						nodeAttr[(s.strDoneLabel)] = it[(strDoneLabelOriginal)]
					}
				}
				if (nodeAttr != [:]) {
					dateLastModified = it.lastModifiedAt
					it.attributes = nodeAttr
					nodeAttr = [:]
					it.lastModifiedAt = dateLastModified
				}
			}
		}
		nodeRoot['gtdMindMapLanguage'] = s.strGTDSyncLanguage
	}
}
// If the GTD mind map does not have a language indication, it cannot be converted.
// Add the current language for GTD Sync properties as an attribute to the root node.
else {
	ui.informationMessage(ui.frame, textUtils.getText("gtdSyncConvertMapLanguageMissing",
		"The language indication for the GTD mind map is missing and the mind map cannot be\
 converted to English."))
	nodeRoot['gtdMindMapLanguage'] = s.strGTDSyncLanguage
}
