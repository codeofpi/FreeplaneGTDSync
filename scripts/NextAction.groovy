// @ExecutionModes({ON_SELECTED_NODE})
// This script can be used to:
// (1) turn a normal node into a next action,
// (2) validate values for an existing next action and
// (3) turn a completed action back into a next action.

import org.freeplane.plugin.script.proxy.Proxy
import gtdSync.Support

Proxy.Node nodeRoot = node.map.root
// Check difference in language
if (Support.blnDifferenceInLanguage(nodeRoot)) return
// Collect functions, translations and next action icon from support class
Support s = new Support(nodeRoot)
Date dateThreshold
Date dateWhen
boolean blnIconToday = false

// Check if Today icon is present on node
node.icons.icons.each {
	if (it == s.strIconToday){
		blnIconToday = true;
	}

}
// Remove current icons and add next action icon to node and today icon if necessary
if (blnIconToday) {
	s.ReplaceIcons(node, s.strIconNextAction, s.strIconToday)
}
else {
	s.ReplaceIcons(node, s.strIconNextAction)
}

// Convert date strings to dates and back to check validity and impose correct notation.
// Exception: an invalid date will be turned to null, i.e. removed as an attribute value.
dateThreshold = s.textToDate(node[(s.strThresholdLabel)].toString(), s.strThresholdLabel)
dateWhen = s.textToDate(node[(s.strWhenLabel)].toString(), s.strWhenLabel)
// Add/replace the next action attributes.
// Spaces will be removed from the Where attibute
// If the Who attribute has a value, the Where value is changed to Waiting-for
s.NextActionAttributes(node, node[(s.strWhereLabel)].toString(),
	node[(s.strWhoLabel)].toString(), dateThreshold, dateWhen, null)
// If there were any exceptions, display the messages.
if (s.strExceptionMessage) {
	ui.informationMessage(ui.frame, s.strExceptionMessage)
	s.strExceptionMessage = ""
}
