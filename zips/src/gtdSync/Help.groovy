package gtdSync

import java.io.File
import org.freeplane.core.resources.ResourceController
import org.freeplane.core.ui.components.UITools
import org.freeplane.features.mode.Controller
import org.freeplane.plugin.script.proxy.Proxy

class Help {
	final ResourceController resourceController = ResourceController.resourceController
	final String strUserDir=resourceController.freeplaneUserDirectory
	final String strLanguageCode = resourceController.languageCode
	Help(String strHelpFile) {
		String strPathname = strUserDir + File.separator + "doc" + File.separator + strHelpFile
		File filePathname = new File(strPathname)
		String strPathnameLC = strPathname
		strPathnameLC -= ".mm"
		strPathnameLC += "_" + strLanguageCode + ".mm"
		File filePathnameLC = new File(strPathnameLC)
		if (filePathnameLC.exists()) filePathname = filePathnameLC
		if (filePathname.exists()){
			URL urlHelpFile = filePathname.toURI().toURL()
			Controller.currentModeController.mapController.newDocumentationMap(urlHelpFile)
		}
		else {
			UITools.informationMessage(UITools.frame, "Help file missing: $strHelpFile")
		}
	}
}	
