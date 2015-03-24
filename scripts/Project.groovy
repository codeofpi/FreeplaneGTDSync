// @ExecutionModes({ON_SELECTED_NODE})
// This script turns a normal node into a project node

import org.freeplane.plugin.script.proxy.Proxy
import gtdSync.Support

Proxy.Node nodeRoot = node.map.root
// Check difference in language
if (Support.blnDifferenceInLanguage(nodeRoot)) return
// Collect functions, translations and project icon from support class
Support s = new Support(nodeRoot)

// Remove current icons and add project icon to node
s.ReplaceIcons(node, s.strIconProject)
// If the project name contains spaces, the words will be capitalised and the spaces will be
// removed.
if (node.text =~ /\s/) {
	node.text = s.removeWhitespace(node.text, s.strProjectLabel)
}
// If spaces have been removed from the project name, display it in a message.
if (s.strExceptionMessage) {
	ui.informationMessage(ui.frame, s.strExceptionMessage)
	s.strExceptionMessage = ""
}

