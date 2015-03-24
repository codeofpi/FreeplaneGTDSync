// @ExecutionModes({ON_SELECTED_NODE})
// This script can be used to:
// (1) mark a next action as completed,
// (2) validate values for an existing completed action and
// (3) turn a normal node into a completed action.

import org.freeplane.plugin.script.proxy.Proxy
import gtdSync.Support

Proxy.Node nodeRoot = node.map.root
// Check difference in language
if (Support.blnDifferenceInLanguage(nodeRoot)) return
// Collect functions and translations from support class
Support s = new Support(nodeRoot)
Date dateThreshold
Date dateWhen
Date dateDone

// Remove current icons and add icons for completed action to node.
s.ReplaceIcons(node, s.strIconDone)
// Convert date strings to dates and back to check validity and impose correct notation.
// Exception: an invalid date will be turned to null, i.e. removed as an attribute value.
dateThreshold = s.textToDate(node[(s.strThresholdLabel)].toString(), s.strThresholdLabel)
dateWhen = s.textToDate(node[(s.strWhenLabel)].toString(), s.strWhenLabel)
dateDone = s.textToDate(node[(s.strDoneLabel)].toString(), s.strDoneLabel)
// If there is no date Done the current date is used.
if (dateDone == null) { dateDone = new Date() }
// Add/replace the next action attributes.
// Spaces will be removed from the Where attibute
// If the Who attribute has a value, the Where value is changed to Waiting-for
s.NextActionAttributes(node, node[(s.strWhereLabel)].toString(),
	node[(s.strWhoLabel)].toString(), dateThreshold, dateWhen, dateDone)
// If there were any exceptions, display the messages.
if (s.strExceptionMessage) {
	ui.informationMessage(ui.frame, s.strExceptionMessage)
	s.strExceptionMessage = ""
}
