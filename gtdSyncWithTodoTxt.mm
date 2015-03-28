<map version="freeplane 1.3.0">
<!--To view this file, download free mind mapping software Freeplane from http://freeplane.sourceforge.net -->
<attribute_registry SHOW_ATTRIBUTES="hide"/>
<node TEXT="GTD Sync with todo.txt" ID="ID_1723255651" CREATED="1283093380553" MODIFIED="1427518682414" BACKGROUND_COLOR="#97c7dc" LINK="http://members.casema.nl/henk.vanden.akker/">
<font SIZE="16" BOLD="true" ITALIC="true"/>
<hook NAME="MapStyle">
    <properties show_icon_for_attributes="true" show_note_icons="true"/>

<map_styles>
<stylenode LOCALIZED_TEXT="styles.root_node">
<stylenode LOCALIZED_TEXT="styles.predefined" POSITION="right">
<stylenode LOCALIZED_TEXT="default" MAX_WIDTH="600" COLOR="#000000" STYLE="as_parent">
<font NAME="SansSerif" SIZE="10" BOLD="false" ITALIC="false"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.details"/>
<stylenode LOCALIZED_TEXT="defaultstyle.note"/>
<stylenode LOCALIZED_TEXT="defaultstyle.floating">
<edge STYLE="hide_edge"/>
<cloud COLOR="#f0f0f0" SHAPE="ROUND_RECT"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.user-defined" POSITION="right">
<stylenode LOCALIZED_TEXT="styles.topic" COLOR="#18898b" STYLE="fork">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.subtopic" COLOR="#cc3300" STYLE="fork">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.subsubtopic" COLOR="#669900">
<font NAME="Liberation Sans" SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.important">
<icon BUILTIN="yes"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.AutomaticLayout" POSITION="right">
<stylenode LOCALIZED_TEXT="AutomaticLayout.level.root" COLOR="#000000">
<font SIZE="18"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,1" COLOR="#0033ff">
<font SIZE="16"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,2" COLOR="#00b439">
<font SIZE="14"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,3" COLOR="#990000">
<font SIZE="12"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,4" COLOR="#111111">
<font SIZE="10"/>
</stylenode>
</stylenode>
</stylenode>
</map_styles>
</hook>
<hook NAME="AutomaticEdgeColor" COUNTER="3"/>
<attribute_layout NAME_WIDTH="121" VALUE_WIDTH="126"/>
<attribute NAME="name" VALUE="gtdSyncWithTodoTxt"/>
<attribute NAME="version" VALUE="v0.7.1"/>
<attribute NAME="author" VALUE="Henk van den Akker"/>
<attribute NAME="freeplaneVersionFrom" VALUE="1.3.11"/>
<attribute NAME="freeplaneVersionTo" VALUE=""/>
<attribute NAME="updateUrl" VALUE=""/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      The homepage of this add-on should be set as the link of the root node.
    </p>
    <p>
      The basic properties of this add-on. They can be used in script names and other attributes, e.g. &quot;${name}.groovy&quot;.
    </p>
    <ul>
      <li>
        name: The name of the add-on, normally a technically one (no spaces, no special characters except _.-).
      </li>
      <li>
        author: Author's name(s) and (optionally) email adresses.
      </li>
      <li>
        version: Since it's difficult to protect numbers like 1.0 from Freeplane's number parser it's advised to prepend a 'v' to the number, e.g. 'v1.0'.
      </li>
      <li>
        freeplane-version-from: The oldest compatible Freeplane version. The add-on will not be installed if the Freeplane version is too old.
      </li>
      <li>
        freeplane-version-to: Normally empty: The newest compatible Freeplane version. The add-on will not be installed if the Freeplane version is too new.
      </li>
      <li>
        updateUrl: URL of the file containing information (version, download url) on the latest version of this add-on. By default: &quot;${homepage}/version.properties&quot;
      </li>
    </ul>
  </body>
</html>

</richcontent>
<node TEXT="description" POSITION="left" ID="ID_1383724249" CREATED="1390267005832" MODIFIED="1427066929477">
<edge COLOR="#ff0000"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      Description would be awkward to edit as an attribute.
    </p>
    <p>
      So you have to put the add-on description as a child of the <i>'description'</i>&#160;node.
    </p>
    <p>
      To translate the description you have to define a translation for the key 'addons.${name}.description'.
    </p>
  </body>
</html>

</richcontent>
<node ID="ID_1854119050" CREATED="1390267912705" MODIFIED="1425833723044"><richcontent TYPE="NODE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      <font face="Verdana">Groovy script to synchronise a Freeplane|GTD mind map with todo.txt. The catch is that you can make your Next Action list available to other applications and systems. For example, you can use Dropbox to distribute todo.txt and use SimpleTask for Android to consult and maintain todo.txt. What does GTD sync do? </font>
    </p>
    <ul>
      <li>
        <font face="Verdana">It imports new next actions from todo.txt to your GTD mind map under node 'New'.</font>
      </li>
      <li>
        <font face="Verdana">It updates existing next actions in your GTD mind map with the changes made in todo.txt since last synchronisation. This includes changing the status to completed. </font>
      </li>
      <li>
        <font face="Verdana">In case a next action is changed in both todo.txt and the GTD mind map, the attributes changed in todo.txt are logged, so after the synchronisation you are able to check whether any manual update is necessary. </font>
      </li>
      <li>
        <font face="Verdana">After the GTD mind map is updated with all new and changed next actions in todo.txt, all its next actions will be exported to todo.txt. The export does not include completed next actions.</font>
      </li>
    </ul>
  </body>
</html>
</richcontent>
</node>
</node>
<node TEXT="changes" POSITION="left" ID="ID_1758370386" CREATED="1390267005867" MODIFIED="1427066929480">
<edge COLOR="#0000ff"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      Change log of this add-on: append one node for each noteworthy version and put the details for each version into a child node.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="v0.1" ID="ID_518764465" CREATED="1390782070654" MODIFIED="1390782087046">
<node TEXT="Set up the foundation of the gtdSyncWithTodoTxt script: file handling, initialisation of important parameters, high level warning and information messages and translation to Dutch" ID="ID_365597270" CREATED="1390782089629" MODIFIED="1393621719199"/>
</node>
<node TEXT="v0.2" ID="ID_723906700" CREATED="1391518276944" MODIFIED="1391518282297">
<node TEXT="Added class Support which supports all three scripts with common translations and functions. Adapted scripts NextAction and CompletedAction to use the new class." ID="ID_1527112129" CREATED="1391517784175" MODIFIED="1391518340814"/>
</node>
<node TEXT="v0.3" ID="ID_364259562" CREATED="1392765219508" MODIFIED="1392765229661">
<node TEXT="Added class NextActionHandler with function to parse lines from todo.txt file. Extended gtdSyncWithTodoTxt script to parse history snapshot." ID="ID_1882886635" CREATED="1392765233508" MODIFIED="1405867959511"/>
</node>
<node TEXT="v0.4" ID="ID_637479779" CREATED="1393620816931" MODIFIED="1393620826421">
<node TEXT="Extended class NextActionHandler with functions to update GTD mind map with changed and new next actions. Extended gtdSyncWithTodoTxt script to use these new functions. Upgraded add-on to standards used by Freeplane version 1.3.xx" ID="ID_697090869" CREATED="1393620830027" MODIFIED="1425833779380"/>
</node>
<node TEXT="v0.5" ID="ID_1881847273" CREATED="1405867762470" MODIFIED="1405867768443">
<node TEXT="Extended script gtdSyncWithTodoTxt.groovy and NextActionHandler class with export from GTD mind map to todo.txt" ID="ID_15205763" CREATED="1405867792149" MODIFIED="1425833811801"/>
</node>
<node TEXT="v0.6" ID="ID_1319923830" CREATED="1408200789392" MODIFIED="1408200801248">
<node TEXT="Added GTD Sync help file" ID="ID_1907406806" CREATED="1408200806461" MODIFIED="1408200998987"/>
</node>
<node TEXT="v0.7" ID="ID_440586698" CREATED="1425727857630" MODIFIED="1425727882946">
<node ID="ID_731831638" CREATED="1425730230217" MODIFIED="1426980632601"><richcontent TYPE="NODE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      <b><font size="5">Beta release</font></b>
    </p>
    <p>
      Changes:
    </p>
    <ul>
      <li>
        Added customisable icon for done,
      </li>
      <li>
        Added check whether current language matches language of GTD mind map to the 4 functions: GTD Sync, Project, Next action and Completed.
      </li>
      <li>
        Added script to convert keywords in GTD mind map from translation in mind map to current translation used by GTD Sync
      </li>
      <li>
        Added quick start help in English and Dutch
      </li>
      <li>
        Added function to call help file (adapted from scriptlib add-on by Jokro)
      </li>
      <li>
        Moved extended help from original help mind map to sourceforge wiki
      </li>
    </ul>
  </body>
</html>
</richcontent>
</node>
</node>
<node TEXT="v0.7.1" ID="ID_247712498" CREATED="1427424334873" MODIFIED="1427424345694">
<node ID="ID_381775610" CREATED="1427424425903" MODIFIED="1427424607213"><richcontent TYPE="NODE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      Changes:
    </p>
    <ul>
      <li>
        Added option to remove node attributes in &quot;Project&quot; function, see user story: https://sourceforge.net/p/gtdsync/tickets/1
      </li>
    </ul>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
<node TEXT="license" POSITION="left" ID="ID_1082168367" CREATED="1390267005873" MODIFIED="1427066929639">
<edge COLOR="#00ff00"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      The add-ons's license that the user has to accept before she can install it.
    </p>
    <p>
      
    </p>
    <p>
      The License text has to be entered as a child of the <i>'license'</i>&#160;node, either as plain text or as HTML.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="&#xa;This add-on is free software: you can redistribute it and/or modify&#xa;it under the terms of the GNU General Public License as published by&#xa;the Free Software Foundation, either version 2 of the License, or&#xa;(at your option) any later version.&#xa;&#xa;This program is distributed in the hope that it will be useful,&#xa;but WITHOUT ANY WARRANTY; without even the implied warranty of&#xa;MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.&#xa0;&#xa0;See the&#xa;GNU General Public License for more details.&#xa;" ID="ID_988929156" CREATED="1390267005880" MODIFIED="1408816191291"/>
</node>
<node TEXT="preferences.xml" POSITION="left" ID="ID_1100648944" CREATED="1390267005905" MODIFIED="1427066929645">
<edge COLOR="#ff00ff"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      <font color="#000000" face="SansSerif, sans-serif">The child node contains the add-on configuration as an extension to mindmapmodemenu.xml (in Tools-&gt;Preferences-&gt;Add-ons). </font>
    </p>
    <p>
      <font color="#000000" face="SansSerif, sans-serif">Every property in the configuration should receive a default value in <i>default.properties</i>&#160;node.</font>
    </p>
  </body>
</html>

</richcontent>
</node>
<node TEXT="default.properties" POSITION="left" ID="ID_1177656479" CREATED="1390267005920" MODIFIED="1427066929648">
<edge COLOR="#00ffff"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      These properties play together with the preferences: Each property defined in the preferences should have a default value in the attributes of this node.
    </p>
  </body>
</html>

</richcontent>
</node>
<node TEXT="translations" POSITION="left" ID="ID_1198026371" CREATED="1390267005926" MODIFIED="1427066929651">
<edge COLOR="#ffff00"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      The translation keys that this script uses. Define one child node per supported locale. The attributes contain the translations. Define at least
    </p>
    <ul>
      <li>
        'addons.${name}' for the add-on's name
      </li>
      <li>
        'addons.${name}.description' for the description, e.g. in the add-on overview dialog (not necessary for English)
      </li>
      <li>
        'addons.${name}.&lt;scriptname&gt;' for each script since it will be the menu title.
      </li>
    </ul>
  </body>
</html>

</richcontent>
<node TEXT="nl" ID="ID_1844388934" CREATED="1390711563350" MODIFIED="1427424192592">
<attribute_layout NAME_WIDTH="242" VALUE_WIDTH="484"/>
<attribute NAME="addons.${name}" VALUE="GTD Sync met todo.txt"/>
<attribute NAME="addons.${name}.gtdSync" VALUE="GTD Sync"/>
<attribute NAME="addons.${name}.Project" VALUE="Project"/>
<attribute NAME="addons.${name}.NextAction" VALUE="Eerstvolgende actie"/>
<attribute NAME="addons.${name}.CompletedAction" VALUE="Voltooide actie"/>
<attribute NAME="addons.${name}.GTDSyncQuickStart" VALUE="GTD Sync Help: snelle start"/>
<attribute NAME="addons.${name}.ConvertLanguage" VALUE="Converteer GTD mind map naar Nederlands"/>
<attribute NAME="gtdSyncLanguage" VALUE="nl"/>
<attribute NAME="gtdSyncLanguageDifference" VALUE="De huidige GTD Sync taal is Nederlands terwijl {0} is gebruikt voor je GTD mind map. Je moet de taal in de Freeplane instellingen op {0} zetten en Freeplane herstarten of de GTD mind map naar Nederlands omzetten: Extra &gt; GTD Sync with todo.txt &gt; Converteer GTD mind map naar Nederlands."/>
<attribute NAME="gtdSyncLanguageUnkown" VALUE="een onbekende taal"/>
<attribute NAME="gtdSyncConvertMapLanguageMissing" VALUE="De indicatie voor de taal van de GTD mind map mist en deze kan niet geconverteerd worden."/>
<attribute NAME="gtdSyncConvertMapNoNeed" VALUE="Het is niet nodig om deze GTD mind map te converteren, want deze is al in het Nederlands."/>
<attribute NAME="gtdSyncConvertMapNotInstalled" VALUE="De add-on &apos;GTD Sync with todo.txt&apos; is niet ge&#xef;nstalleerd."/>
<attribute NAME="gtdSyncConvertMapNoOriginalLC" VALUE="De taal code in de centrale knoop van de GTD mind map is niet beschikbaar voor add-on &apos;GTD Sync with todo.txt&apos;. Dit kan veroorzaakt zijn door het handmatig wijzigen van de taal code in de centrale knoop van de GTD mind map of door een verouderde versie van &apos;GTD Sync with todo.txt&apos;."/>
<attribute NAME="gtdSyncPathDialogueTitle" VALUE="Selecteer de map voor todo.txt. Draai je GTD sync niet vanuit je GTD mind map, annuleer dan."/>
<attribute NAME="gtdSyncPathSelect" VALUE="Selecteer"/>
<attribute NAME="gtdSyncPathSelectToolTip" VALUE="Selecteer een map waar todo.txt opgeslagen is of moet gaan worden."/>
<attribute NAME="gtdSyncPathCancel" VALUE="Annuleer"/>
<attribute NAME="gtdSyncPathCancelToolTip" VALUE="Annuleer om te voorkomen dat GTD Sync met de verkeerde mind map wordt uitgevoerd."/>
<attribute NAME="gtdSyncPathLookIn" VALUE="Kijk in:"/>
<attribute NAME="gtdSyncPathFilesOfType" VALUE="Bestanden van type"/>
<attribute NAME="gtdSyncPathAllFiles" VALUE="Alle bestanden"/>
<attribute NAME="gtdSyncUpFolderToolTip" VALUE="Map omhoog"/>
<attribute NAME="gtdSyncNewFolderToolTip" VALUE="Een nieuwe, lege map maken"/>
<attribute NAME="gtdSyncNodeNextActionIcon" VALUE="Pictogram: Eerstvolgende actie"/>
<attribute NAME="gtdSyncNodeProjectIcon" VALUE="Pictogram: Project"/>
<attribute NAME="gtdSyncNodeTodayIcon" VALUE="Pictogram: Vandaag"/>
<attribute NAME="gtdSyncNodeDoneIcon" VALUE="Pictogram: Afgerond"/>
<attribute NAME="gtdSyncAttrNameLabel" VALUE="Omschrijving"/>
<attribute NAME="gtdSyncAttrPriorityLabel" VALUE="Prioriteit"/>
<attribute NAME="gtdSyncAttrWhereLabel" VALUE="Context"/>
<attribute NAME="gtdSyncAttrWhoLabel" VALUE="Actiehouder"/>
<attribute NAME="gtdSyncAttrCreatedLabel" VALUE="Gecre&#xeb;erd"/>
<attribute NAME="gtdSyncAttrThresholdLabel" VALUE="Startdatum"/>
<attribute NAME="gtdSyncAttrWhenLabel" VALUE="Einddatum"/>
<attribute NAME="gtdSyncAttrDoneLabel" VALUE="Afgerond"/>
<attribute NAME="gtdSyncAttrProjectLabel" VALUE="Project"/>
<attribute NAME="gtdSyncAttrLinkLabel" VALUE="URL"/>
<attribute NAME="gtdSyncOldLabel" VALUE="Oud"/>
<attribute NAME="gtdSyncNewLabel" VALUE="Nieuw"/>
<attribute NAME="gtdSyncYes" VALUE="Ja"/>
<attribute NAME="gtdSyncNo" VALUE="Nee"/>
<attribute NAME="gtdSyncSettingPattern" VALUE="Pictogram:"/>
<attribute NAME="gtdSyncProjectAttributesTitle" VALUE="Attributen verwijderen"/>
<attribute NAME="gtdSyncProjectAttributesQuestion" VALUE="Deze knoop bevat attributen, mogelijk omdat het een eerstvolgende actie is. Deze attributen verwijderen?"/>
<attribute NAME="gtdSyncExceptionIntroduction" VALUE="Voor de uit {0} verwerkte regel{1}zijn er de volgende meldingen:"/>
<attribute NAME="gtdSyncExceptionIntroductionExport" VALUE="Bij de export van de eerstvolgende actie {0} hebben de volgende correcties plaatsgevonden:"/>
<attribute NAME="gtdSyncExceptionDateReplace" VALUE="Waarde {0} voor datum {1} is ongeldig en is vervangen door de huidige datum."/>
<attribute NAME="gtdSyncException1DateMissing" VALUE="Bij deze afgeronde actie ontbreekt 1 van de 2 datums aan het begin van de regel. De datum {0} is op de aanwezige waarde {2} gezet, de datum {1} op vandaag."/>
<attribute NAME="gtdSyncException2DatesMissing" VALUE="Bij deze afgeronde actie ontbreken 2 datums aan het begin van de regel. De datum {0} en datum {1} zijn op vandaag gezet."/>
<attribute NAME="gtdSyncExceptionDateCreatedMissing" VALUE="Bij deze eerstvolgende actie ontbreekt een datum aan het begin van de regel. De datum {0} is op de datum van vandaag gezet."/>
<attribute NAME="gtdSyncExceptionNoSquareBracket" VALUE="Voor attribuut {0} kan de sluitende haak {1} niet gevonden worden."/>
<attribute NAME="gtdSyncExceptionInfoDate" VALUE="Attribuut {0} bevatte ongeldige datum {1}. Voer de correcte datum in volgens het patroon {2}."/>
<attribute NAME="gtdSyncExceptionWhitespace" VALUE="De witruimte in waarde {0} voor attribuut {1} is verwijderd."/>
<attribute NAME="gtdSyncExceptionDelegated" VALUE="Omdat deze eerstvolgende actie gedelegeerd is, is de waarde van attribuut {0} gewijzigd van {1} naar {2}."/>
<attribute NAME="gtdSyncExceptionNoContext" VALUE="Omdat deze eerstvolgende actie niet gedelegeerd is, is de waarde van attribuut {0} gewijzigd van {2} naar {1}."/>
<attribute NAME="gtdSyncExceptionURL" VALUE="{0} is geen geldige URL en is verwijderd."/>
<attribute NAME="gtdSyncExceptionNoHistory" VALUE="De wijzigingen sinds de laatste synchronisatie kunnen niet vastgesteld worden omdat de historische momentopname mist. Het wordt aangenomen dat alle attributen gewijzigd zijn."/>
<attribute NAME="gtdSyncExceptionURInotSupported" VALUE="Deze eerstvolgende actie bevat de link {0}. GTD Sync ondersteunt en exporteert dit type link niet."/>
<attribute NAME="gtdSyncNotImportedFirstSync" VALUE="NIET GE&#xcf;MPORTEERD: Deze todo.txt regel bevat een knoop ID, alsof deze tijdens een vorige synchronisatie uit de GTD mind map ge&#xeb;xporteerd is. Dit kan echter niet, omdat dit de eerste synchronisatie is."/>
<attribute NAME="gtdSyncNotImportedNodeMissing" VALUE="NIET GE&#xcf;MPORTEERD: Deze bestaande eerstvolgende actie kon niet in de GTD mind map gevonden worden en de volgende wijzigingen konden daarom niet doorgevoerd worden:"/>
<attribute NAME="gtdSyncNotImportedNodeChanged" VALUE="NIET GE&#xcf;MPORTEERD: De corresponderende eerstvolgende actie in de mind map is ook gewijzigd sinds de vorige synchronisatie. Aan de hand van onderstaand overzicht kan je de eerstvolgende actie in de mind map handmatig wijzigen."/>
<attribute NAME="gtdSyncNotImportedNotUpdatable" VALUE="NIET GE&#xcf;MPORTEERD: Het is niet mogelijk de mind map met de volgende wijzigingen bij te werken:"/>
<attribute NAME="gtdSyncNotUpdatableCreated" VALUE="Reden: deze datum wordt na het opvoeren van een eerstvolgende actie nooit meer gewijzigd. Mogelijk is dit per ongeluk wel gebeurd."/>
<attribute NAME="gtdSyncNotUpdatableProject" VALUE="Reden: er kan niet automatisch afgeleid worden wat de nieuwe positie van deze eerstvolgende actie in de GTD mind map moet zijn. Zet deze daarom handmatig op de juiste positie."/>
<attribute NAME="gtdSyncImported" VALUE="GE&#xcf;MPORTEERD:"/>
<attribute NAME="gtdSyncWaitingFor" VALUE="Wachten-op"/>
<attribute NAME="gtdSyncEmpty" VALUE="leeg"/>
<attribute NAME="gtdSyncAnywhere" VALUE="_overal"/>
<attribute NAME="gtdSyncUnknown" VALUE="Onbekend"/>
<attribute NAME="gtdSyncLinkOldDefault" VALUE="www.onbekend.nl"/>
<attribute NAME="gtdSyncResultTitle" VALUE="Resultaat van GTD synchronisatie"/>
<attribute NAME="gtdSyncResultCancellation" VALUE="De synchronisatie is geannuleerd omdat er geen map voor todo.txt is gespecificeerd."/>
<attribute NAME="gtdSyncResultCompleted" VALUE="&lt;p&gt;Synchronisatie voltooid.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultFirstSync" VALUE="&lt;br&gt;&lt;p&gt;Dit was de eerste synchronisatie.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtHistoryMissing" VALUE="&lt;br&gt;&lt;p&gt;&lt;b&gt;Waarschuwing:&lt;/b&gt; de kopie van bij de vorige synchronisatie ge&#xeb;xporteerde acties ontbrak. Er wordt nu aangenomen dat alle bestaande acties gewijzigd zijn.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtHistoryWarnings" VALUE="&lt;p&gt;&lt;b&gt;Waarschuwing:&lt;/b&gt; er waren {0} opmerkingen en/of correcties bij het inlezen van bij de vorige synchronisatie ge&#xeb;xporteerde eerstvolgende acties. Dit kan erop wijzen dat het bestand {1} handmatig gewijzigd is. Dit is niet de bedoeling.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultDifferentCounts" VALUE="&lt;p&gt;&lt;b&gt;Waarschuwing:&lt;/b&gt; het aantal bij de vorige synchronisatie ge&#xeb;xporteerde eerstvolgende acties, {0}, wijkt af van het aantal bestaande acties in todo.txt, {1}. Mogelijk zijn er per ongeluk acties of node IDs verwijderd.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLastSyncDateMissing" VALUE="&lt;p&gt;&lt;b&gt;Waarschuwing:&lt;/b&gt; de datum van de vorige synchronisatie ontbrak. Hierdoor kon niet bepaald worden of de eerstvolgende acties in de mind map tussentijds zijn gewijzigd. Er is aangenomen dat zij allemaal tussentijds zijn gewijzigd en uit voorzorg wordt dan geen van de eventuele wijzingingen uit todo.txt doorgevoerd. Als dit voorkwam staat hieronder aangegeven dat een aantal gewijzigde acties niet zijn ge&#xef;mporteerd en wordt er voor details doorverwezen naar het logbestand.&lt;/p&gt;&lt;br&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtMissing" VALUE="&lt;p&gt;&lt;b&gt;Waarschuwing:&lt;/b&gt; er was geen todo.txt aanwezig terwijl deze er wel behoorde te zijn aangezien dit niet de eerste synchronisatie was. Als er acties in de todo.txt waren gewijzigd of toegevoegd, zijn deze met het verdwijnen van dit bestand verloren gegaan.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtMissingFirstSync" VALUE="&lt;p&gt;Er was geen todo.txt aanwezig dus er zijn geen eerstvolgende acties ge&#xef;mporteerd.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxt" VALUE="&lt;p&gt;Er zijn in totaal {0} eerstvolgende acties uit todo.txt verwerkt, waarvan:&lt;/p&gt;&lt;ul style={2}&gt;&lt;li&gt;{1} nieuwe eerstvolgende acties"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtNewWithWarning" VALUE=", waarvan:&lt;ul style={1}&gt;&lt;li&gt;{0} eerstvolgende acties met opmerkingen en/of correcties&lt;/li&gt;&lt;/ul&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExisting" VALUE="&lt;/li&gt;&lt;li&gt;{1} van {0} bestaande eerstvolgende acties zijn gewijzigd"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingChanged" VALUE=", waarvan:&lt;ul style={0}&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingWithWarning" VALUE="&lt;li&gt;{0} eerstvolgende acties met opmerkingen en/of correcties&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingNotImported" VALUE="&lt;li&gt;{0} eerstvolgende acties niet ge&#xef;mporteerd zijn&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsExported" VALUE="&lt;br&gt;&lt;p&gt;Er zijn {0} eerstvolgende acties ge&#xeb;xporteerd naar todo.txt"/>
<attribute NAME="gtdSyncResultNextActionsExportedList" VALUE=", met:&lt;ul style={0}&gt;"/>
<attribute NAME="gtdSyncResultProjectsCorrected" VALUE="&lt;li&gt;{0} gecorrigeerde projecten&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsExportedWithWarning" VALUE="&lt;li&gt;{0} gecorrigeerde eerstvolgende acties&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultLogNotImported" VALUE="&lt;br&gt;&lt;p&gt;Sommige bestaande eerstvolgende acties in de GTD mind map konden niet bijgewerkt worden met de wijzigingen uit todo.txt. U kunt deze wijzigingen in het Freeplane logbestand reviewen en de eerstvolgende acties handmatig aanpassen als dat nodig is. Het logbestand bevat mogelijk ook informatie over correcties op eerstvolgende acties.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLogCorrections" VALUE="&lt;br&gt;&lt;p&gt;Voor details over gecorrigeerde eerstvolgende acties, zie het Freeplane logbestand.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultNoLog" VALUE="&lt;br&gt;&lt;p&gt;Er zijn geen overgeslagen of gecorrigeerde eerstvolgende acties.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLogButtonText" VALUE="&lt;HTML&gt;&lt;CENTER&gt;Raadpleeg het Freeplane logbestand&lt;/CENTER&gt;&lt;/HTML&gt;"/>
<font BOLD="false"/>
</node>
<node TEXT="en" ID="ID_1505941754" CREATED="1390267005931" MODIFIED="1427423627670">
<attribute_layout NAME_WIDTH="242" VALUE_WIDTH="484"/>
<attribute NAME="addons.${name}" VALUE="GTD Sync with todo.txt"/>
<attribute NAME="addons.${name}.gtdSync" VALUE="GTD Sync"/>
<attribute NAME="addons.${name}.Project" VALUE="Project"/>
<attribute NAME="addons.${name}.NextAction" VALUE="Next action"/>
<attribute NAME="addons.${name}.CompletedAction" VALUE="Completed"/>
<attribute NAME="addons.${name}.GTDSyncQuickStart" VALUE="GTD Sync Help: quick start"/>
<attribute NAME="addons.${name}.ConvertLanguage" VALUE="Convert GTD mind map to English"/>
<attribute NAME="gtdSyncLanguage" VALUE="en"/>
<attribute NAME="gtdSyncLanguageDifference" VALUE="The current GTD Sync language is English while {0} has been used for your GTD mind map. You should change the language in the Freeplane settings to {0} and restart Freeplane or convert the GTD mind map to English: from the menu choose: Tools &gt; GTD Sync with todo.txt &gt; Convert GTD mind map to English."/>
<attribute NAME="gtdSyncLanguageUnkown" VALUE="an unknown language"/>
<attribute NAME="gtdSyncConvertMapLanguageMissing" VALUE="The language indication for the GTD mind map is missing and the mind map cannot be converted to English."/>
<attribute NAME="gtdSyncConvertMapNoNeed" VALUE="There is no need to convert this GTD mind map because it is already using English."/>
<attribute NAME="gtdSyncConvertMapNotInstalled" VALUE="The add-on &apos;GTD Sync with todo.txt&apos; is not installed."/>
<attribute NAME="gtdSyncConvertMapNoOriginalLC" VALUE="The language code in the root node of the GTD mind map is not available for add-on &apos;GTD Sync with todo.txt&apos;. This could be caused by the fact that the language code in the root node of the GTD mind map has been changed manually or that an older version of &apos;GTD Sync with todo.txt&apos; is installed."/>
<attribute NAME="gtdSyncPathDialogueTitle" VALUE="Select folder for todo.txt. If you do not run GTD sync from your GTD mind map, cancel!"/>
<attribute NAME="gtdSyncPathSelect" VALUE="Select"/>
<attribute NAME="gtdSyncPathSelectToolTip" VALUE="Select a folder where todo.txt is stored or is going to be stored."/>
<attribute NAME="gtdSyncPathCancel" VALUE="Cancel"/>
<attribute NAME="gtdSyncPathCancelToolTip" VALUE="Cancel to prevent that GTD is run on the wrong mind map."/>
<attribute NAME="gtdSyncPathLookIn" VALUE="Look in:"/>
<attribute NAME="gtdSyncPathFilesOfType" VALUE="Files of Type"/>
<attribute NAME="gtdSyncPathAllFiles" VALUE="All Files"/>
<attribute NAME="gtdSyncUpFolderToolTip" VALUE="Up One Level"/>
<attribute NAME="gtdSyncNewFolderToolTip" VALUE="Create New Folder"/>
<attribute NAME="gtdSyncNodeNextActionIcon" VALUE="Icon: Next action"/>
<attribute NAME="gtdSyncNodeProjectIcon" VALUE="Icon: Project"/>
<attribute NAME="gtdSyncNodeTodayIcon" VALUE="Icon: Today"/>
<attribute NAME="gtdSyncNodeDoneIcon" VALUE="Icon: Done"/>
<attribute NAME="gtdSyncAttrNameLabel" VALUE="Description"/>
<attribute NAME="gtdSyncAttrPriorityLabel" VALUE="Priority"/>
<attribute NAME="gtdSyncAttrWhereLabel" VALUE="Where"/>
<attribute NAME="gtdSyncAttrWhoLabel" VALUE="Who"/>
<attribute NAME="gtdSyncAttrCreatedLabel" VALUE="Created"/>
<attribute NAME="gtdSyncAttrThresholdLabel" VALUE="Threshold"/>
<attribute NAME="gtdSyncAttrWhenLabel" VALUE="When"/>
<attribute NAME="gtdSyncAttrDoneLabel" VALUE="Done"/>
<attribute NAME="gtdSyncAttrProjectLabel" VALUE="Project"/>
<attribute NAME="gtdSyncAttrLinkLabel" VALUE="URL"/>
<attribute NAME="gtdSyncOldLabel" VALUE="Old"/>
<attribute NAME="gtdSyncNewLabel" VALUE="New"/>
<attribute NAME="gtdSyncYes" VALUE="Yes"/>
<attribute NAME="gtdSyncNo" VALUE="No"/>
<attribute NAME="gtdSyncSettingPattern" VALUE="Icon:"/>
<attribute NAME="gtdSyncProjectAttributesTitle" VALUE="Remove attributes"/>
<attribute NAME="gtdSyncProjectAttributesQuestion" VALUE="This node has attributes, possibly because it is a next action. Remove the attributes?"/>
<attribute NAME="gtdSyncExceptionIntroduction" VALUE="Processing line{1}from file {0} resulted in the following messages:"/>
<attribute NAME="gtdSyncExceptionIntroductionExport" VALUE="With the export of next action {0} the following corrections took place:"/>
<attribute NAME="gtdSyncExceptionDateReplace" VALUE="Value {0} for date {1} is invalid and has been replaced with the current date."/>
<attribute NAME="gtdSyncException1DateMissing" VALUE="For this completed action 1 of the 2 dates at the beginning of the line is missing. Date {0} has been set to the present value {2}, date {1} to today."/>
<attribute NAME="gtdSyncException2DatesMissing" VALUE="For this completed action 2 dates at the beginning of the line are missing. Date {0} and date {1} have been set to today."/>
<attribute NAME="gtdSyncExceptionDateCreatedMissing" VALUE="For this next action a date is missing at the beginning of the line. Date {0} has been set to today."/>
<attribute NAME="gtdSyncExceptionNoSquareBracket" VALUE="For attribute {0} the closing bracket {1} cannot be found."/>
<attribute NAME="gtdSyncExceptionInfoDate" VALUE="Attribute {0} contained invalid date {1}. Enter the correct date in format {2}."/>
<attribute NAME="gtdSyncExceptionWhitespace" VALUE="The whitespace in value {0} for attribute {1} has been removed."/>
<attribute NAME="gtdSyncExceptionDelegated" VALUE="Since this next action is delegated, the value for attribute {0} has been changed from {1} to {2}."/>
<attribute NAME="gtdSyncExceptionNoContext" VALUE="Since this next action is not delegated, the value for attribute {0} has been changed from {2} to {1}."/>
<attribute NAME="gtdSyncExceptionURL" VALUE="{0} is not a valid URL and has been removed."/>
<attribute NAME="gtdSyncExceptionNoHistory" VALUE="Changes since last sync cannot be determined because the historic record is missing. It is assumed that all attributes have changed."/>
<attribute NAME="gtdSyncExceptionURInotSupported" VALUE="This next action contains the link {0}. GTD Sync does not support and export this type of link."/>
<attribute NAME="gtdSyncNotImportedFirstSync" VALUE="NOT IMPORTED: This todo.txt line contains a node ID, like it has been exported from the GTD mind map during a previous synchronisation. However, this is not possible since this is the first synchronisation."/>
<attribute NAME="gtdSyncNotImportedNodeMissing" VALUE="NOT IMPORTED: This existing next action could not be found in the GTD mind map and because of that it was not possible to apply the following changes:"/>
<attribute NAME="gtdSyncNotImportedNodeChanged" VALUE="NOT IMPORTED: The corresponding next action in the mind map has also been changed since last synchronisation. Based on the overview below you can change the next action in the mind map manually."/>
<attribute NAME="gtdSyncNotImportedNotUpdatable" VALUE="NOT IMPORTED: It is not possible to update the mind map foor the following changes:"/>
<attribute NAME="gtdSyncNotUpdatableCreated" VALUE="Cause: this date is never changed after the creation of a next action. Possibly this has happened by accident."/>
<attribute NAME="gtdSyncNotUpdatableProject" VALUE="Cause: the new position of this next action in the mind map cannot be derived automatically. Because of this you have to reposition this next action manually."/>
<attribute NAME="gtdSyncImported" VALUE="IMPORTED:"/>
<attribute NAME="gtdSyncWaitingFor" VALUE="Waiting-for"/>
<attribute NAME="gtdSyncEmpty" VALUE="empty"/>
<attribute NAME="gtdSyncAnywhere" VALUE="_anywhere"/>
<attribute NAME="gtdSyncUnknown" VALUE="Unknown"/>
<attribute NAME="gtdSyncLinkOldDefault" VALUE="www.unknown.com"/>
<attribute NAME="gtdSyncResultTitle" VALUE="Result of GTD synchronisation"/>
<attribute NAME="gtdSyncResultCancellation" VALUE="Synchronisation is cancelled because no folder for todo.txt has been specified."/>
<attribute NAME="gtdSyncResultCompleted" VALUE="&lt;p&gt;Synchronisation completed.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultFirstSync" VALUE="&lt;br&gt;&lt;p&gt;This was the first synchronisation.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtHistoryMissing" VALUE="&lt;br&gt;&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; the snapshot of the exported next actions during last synchronisation is missing. To prevent loss of potential changes it has been assumed that all existing next actions in todo.txt have changed since last synchronisation.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtHistoryWarnings" VALUE="&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; there were {0} remarks and/or corrections reading the next actions exported last synchronisation. This could be caused by manually changing the file {1}. This file should not be changed.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultDifferentCounts" VALUE="&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; the number of exported next actions in last synchronisation, {0}, differs from the number of existing actions in todo.txt, {1}. Possibly next actions or node id were removed by accident.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLastSyncDateMissing" VALUE="&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; the date of the last synchronisation was missing. Because of this it could not be determined whether next actions in the mind map have changed in the meantime. It is assumed that all of them have changed and out of precaution potential updates from todo.txt have been skipped. If so, it is indicated that the changed next action have not been imported and there is a reference to the details in the logfile.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtMissing" VALUE="&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; there was no todo.txt present. It was expected to be present since this is not the first synchronisation. If there were any new or changed actions, they have been lost with the todo.txt file.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultTodoTxtMissingFirstSync" VALUE="&lt;p&gt;There was no todo.txt present and thus no next actions have been imported.&lt;/p&gt;&lt;br&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxt" VALUE="&lt;p&gt;A total of {0} next actions from todo.txt have been processed of which:&lt;p&gt;&lt;ul style={2}&gt;&lt;li&gt;{1} new next actions"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtNewWithWarning" VALUE=", of which:&lt;ul style={1}&gt;&lt;li&gt;{0} next actions with remarks and/or corrections&lt;/li&gt;&lt;/ul&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExisting" VALUE="&lt;/li&gt;&lt;li&gt;{1} of {0} existing next actions have been changed"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingChanged" VALUE=", of which:&lt;ul style={0}&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingWithWarning" VALUE="&lt;li&gt;{0} next actions with remarks and/or corrections&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsTodoTxtExistingNotImported" VALUE="&lt;li&gt;{0} next actions have not been imported&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsExported" VALUE="&lt;br&gt;&lt;p&gt;{0} next actions were exported to todo.txt"/>
<attribute NAME="gtdSyncResultNextActionsExportedList" VALUE=", with:&lt;ul style={0}&gt;"/>
<attribute NAME="gtdSyncResultProjectsCorrected" VALUE="&lt;li&gt;{0} corrected projects&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultNextActionsExportedWithWarning" VALUE="&lt;li&gt;{0} corrected next actions&lt;/li&gt;"/>
<attribute NAME="gtdSyncResultLogNotImported" VALUE="&lt;br&gt;&lt;p&gt;Some existing next actions in the GTD mind map have not been updated with the changes from todo.txt. You can review these changes in the Freeplane logfile and change the next actions manually if necessary. The logfile may also contain information regarding correction on next actions.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLogCorrections" VALUE="&lt;br&gt;&lt;p&gt;For details on corrected next actions, see the Freeplane logfile.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultNoLog" VALUE="&lt;br&gt;&lt;p&gt;There are no skipped or corrected next actions.&lt;/p&gt;"/>
<attribute NAME="gtdSyncResultLogButtonText" VALUE="&lt;HTML&gt;&lt;CENTER&gt;Consult the Freeplane logfile&lt;/CENTER&gt;&lt;/HTML&gt;"/>
</node>
</node>
<node TEXT="scripts" POSITION="right" ID="ID_1619523350" CREATED="1390267005954" MODIFIED="1427066929664">
<edge COLOR="#00007c"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      An add-on may contain multiple scripts. The node text defines the script name (e.g. inserInlineImage.groovy). The name must have a suffix of a supported script language like .groovy or .js and may only consist of letters and digits. The script properties have to be configured via attributes:
    </p>
    <p>
      
    </p>
    <p>
      * menuLocation: &lt;locationkey&gt;
    </p>
    <p>
      &#160;&#160;&#160;- Defines where the menu location.
    </p>
    <p>
      &#160;&#160;&#160;-&#160;See mindmapmodemenu.xml for how the menu locations look like.
    </p>
    <p>
      &#160;&#160;&#160;- http://freeplane.bzr.sf.net/bzr/freeplane/freeplane_program/trunk/annotate/head%3A/freeplane/resources/xml/mindmapmodemenu.xml
    </p>
    <p>
      &#160;&#160;&#160;- This attribute is mandatory
    </p>
    <p>
      
    </p>
    <p>
      * menuTitleKey: &lt;key&gt;
    </p>
    <p>
      &#160;&#160;&#160;- The menu item title will be looked up under the translation key &lt;key&gt; - don't forget to define its translation.
    </p>
    <p>
      &#160;&#160;&#160;- This attribute is mandatory
    </p>
    <p>
      
    </p>
    <p>
      * executionMode: &lt;mode&gt;
    </p>
    <p>
      &#160;&#160;&#160;- The execution mode as described in the Freeplane wiki (http://freeplane.sourceforge.net/wiki/index.php/Scripting)
    </p>
    <p>
      &#160;&#160;&#160;- ON_SINGLE_NODE: Execute the script once. The <i>node</i>&#160;variable is set to the selected node.
    </p>
    <p>
      &#160;&#160;&#160;- ON_SELECTED_NODE: Execute the script n times for n selected nodes, once for each node.
    </p>
    <p>
      &#160;&#160;&#160;- ON_SELECTED_NODE_RECURSIVELY: Execute the script on every selected node and recursively on all of its children.
    </p>
    <p>
      &#160;&#160;&#160;- In doubt use ON_SINGLE_NODE.
    </p>
    <p>
      &#160;&#160;&#160;- This attribute is mandatory
    </p>
    <p>
      
    </p>
    <p>
      * keyboardShortcut: &lt;shortcut&gt;
    </p>
    <p>
      &#160;&#160;&#160;- Optional: keyboard combination / accelerator for this script, e.g. control alt I
    </p>
    <p>
      &#160;&#160;&#160;- Use lowercase letters for modifiers and uppercase for letters. Use no + signs.
    </p>
    <p>
      &#160;&#160;&#160;- The available key names are listed at http://download.oracle.com/javase/1.4.2/docs/api/java/awt/event/KeyEvent.html#VK_0
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;In the list only entries with a 'VK_' prefix count. Omit the prefix in the shortcut definition.
    </p>
    <p>
      
    </p>
    <p>
      * Permissions&#160;that the script(s) require, each either false or true:
    </p>
    <p>
      &#160;&#160;&#160;- execute_scripts_without_asking
    </p>
    <p>
      &#160;&#160;&#160;- execute_scripts_without_file_restriction: permission to read files
    </p>
    <p>
      &#160;&#160;&#160;- execute_scripts_without_write_restriction: permission to create/change/delete files
    </p>
    <p>
      &#160;&#160;&#160;- execute_scripts_without_exec_restriction: permission to execute other programs
    </p>
    <p>
      &#160;&#160;&#160;- execute_scripts_without_network_restriction: permission to access the network
    </p>
    <p>
      &#160;&#160;Notes:
    </p>
    <p>
      &#160;&#160;- The set of permissions is fixed.
    </p>
    <p>
      &#160;&#160;- Don't change the attribute names, don't omit one.
    </p>
    <p>
      &#160;&#160;- Set the values either to true or to false
    </p>
    <p>
      &#160;&#160;- In any case set execute_scripts_without_asking to true unless you want to annoy users.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="gtdSyncWithTodoTxt.groovy" ID="ID_740254855" CREATED="1390268738114" MODIFIED="1426400152109">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.gtdSync"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_single_node"/>
<attribute NAME="keyboardShortcut" VALUE="F5"/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
<node TEXT="Project.groovy" ID="ID_1726103148" CREATED="1391808100503" MODIFIED="1426377595457">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.Project"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_selected_node"/>
<attribute NAME="keyboardShortcut" VALUE="F6"/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
<node TEXT="NextAction.groovy" ID="ID_1636804625" CREATED="1390268757240" MODIFIED="1426400173259">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.NextAction"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_selected_node"/>
<attribute NAME="keyboardShortcut" VALUE="F7"/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
<node TEXT="CompletedAction.groovy" ID="ID_1573587831" CREATED="1390268803101" MODIFIED="1426398216902">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.CompletedAction"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_selected_node"/>
<attribute NAME="keyboardShortcut" VALUE="F8"/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
<node TEXT="GTDSyncQuickStart.groovy" ID="ID_1634456286" CREATED="1426377325870" MODIFIED="1426399626966">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.GTDSyncQuickStart"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_single_node"/>
<attribute NAME="keyboardShortcut" VALUE="alt F1"/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
<node TEXT="ConvertLanguage.groovy" ID="ID_1004897490" CREATED="1422746330114" MODIFIED="1426377471068">
<attribute_layout NAME_WIDTH="253" VALUE_WIDTH="253"/>
<attribute NAME="menuTitleKey" VALUE="addons.${name}.ConvertLanguage"/>
<attribute NAME="menuLocation" VALUE="main_menu_scripting/gtdSyncWithTodoTxt"/>
<attribute NAME="executionMode" VALUE="on_single_node"/>
<attribute NAME="keyboardShortcut" VALUE=""/>
<attribute NAME="execute_scripts_without_asking" VALUE="true"/>
<attribute NAME="execute_scripts_without_file_restriction" VALUE="true"/>
<attribute NAME="execute_scripts_without_write_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_exec_restriction" VALUE="false"/>
<attribute NAME="execute_scripts_without_network_restriction" VALUE="false"/>
</node>
</node>
<node TEXT="zips" POSITION="right" ID="ID_49501171" CREATED="1390267006047" MODIFIED="1427066929821">
<edge COLOR="#007c00"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      An add-on may contain any number of nodes containing zip files.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The immediate child nodes contain a description of the zip. The devtools script releaseAddOn.groovy allows automatic zip creation if the name of this node matches a directory in the current directory.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The child nodes of these nodes contain the actual zip files.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- Any zip file will be extracted in the &lt;installationbase&gt;. Currently, &lt;installationbase&gt; is always Freeplane's &lt;userhome&gt;, e.g. ~/.freeplane/1.3.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The files will be processed in the sequence as seen in the map.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="doc" ID="ID_1507091255" CREATED="1408200850736" MODIFIED="1408200853222"/>
<node TEXT="src" ID="ID_1991600969" CREATED="1391517472720" MODIFIED="1391517479140"/>
</node>
<node TEXT="images" POSITION="right" ID="ID_1763499668" CREATED="1390267006055" MODIFIED="1427066929866">
<edge COLOR="#7c007c"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      An add-on may define any number of images as child nodes of the images node. The actual image data has to be placed as base64 encoded binary data into the text of a subnode.
    </p>
    <p>
      The images are saved to the <i>${installationbase}/resources/images</i>&#160;directory.
    </p>
    <p>
      
    </p>
    <p>
      The following images should be present:
    </p>
    <ul>
      <li>
        <i>${name}-icon.png</i>, like <i>oldicons-theme-icon.png</i>. This will be used in the app-on overview.
      </li>
      <li>
        <i>${name}-screenshot-1.png</i>, like <i>oldicons-theme-screenshot-1.png</i>. This will be used in the app-on details dialog. Further images can be included but they are not used yet.
      </li>
    </ul>
    <p>
      Images can be added automatically by releaseAddOn.groovy or must be uploaded into the map via the script <i>Tools-&gt;Scripts-&gt;Insert Binary</i>&#160;since they have to be (base64) encoded as simple strings.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="${name}.png" ID="ID_675551843" CREATED="1391730079644" MODIFIED="1391730134225"/>
<node TEXT="${name}-icon.png" ID="ID_1279890837" CREATED="1391730135796" MODIFIED="1391730145023"/>
</node>
<node TEXT="lib" POSITION="right" ID="ID_450907911" CREATED="1403402255595" MODIFIED="1427066929809">
<edge COLOR="#7c7c00"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      An add-on may contain any number of nodes containing binary files (normally .jar files) to be added to the add-on's classpath.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The immediate child nodes contain the name of the file, e.g. 'mysql-connector-java-5.1.25.jar'). Put the file into a 'lib' subdirectory of the add-on base directory.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The child nodes of these nodes contain the actual files.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- Any lib file will be extracted in &lt;installationbase&gt;/&lt;addonname&gt;/lib.
    </p>
    <p>
      
    </p>
    <p>
      &#160;- The files will be processed in the sequence as seen in the map.
    </p>
  </body>
</html>

</richcontent>
<node TEXT="gtdSync.jar" ID="ID_1244414329" CREATED="1403402255610" MODIFIED="1403402255612"/>
</node>
<node TEXT="deinstall" POSITION="left" ID="ID_1502490928" CREATED="1403439367804" MODIFIED="1427066980532">
<edge COLOR="#00ff00"/>
<richcontent TYPE="NOTE">

<html>
  <head>
    
  </head>
  <body>
    <p>
      List of files and/or directories to remove on uninstall
    </p>
  </body>
</html>

</richcontent>
<attribute_layout NAME_WIDTH="38" VALUE_WIDTH="405"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}.script.xml"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/gtdSyncWithTodoTxt.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/Project.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/NextAction.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/CompletedAction.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/GTDSyncQuickStart.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/scripts/ConvertLanguage.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/addons/${name}/lib/gtdSync.jar"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/gtdSync_help.mm"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/gtdSync_help_nl.mm"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/AddNextAction.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/AfterSync.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/AreasOfYourLife.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/CheckOffNextAction.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/CompletedAction.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/Confirmation.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/DateSettings.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/DelegateAfter.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/DelegateBefore.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/EnterContextAfter.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/EnterContextBefore.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/F-bar.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/FilterContext.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/MovedNewAction.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/NextActionsAfter.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/NextActionsBefore.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/NodeWithAttributes.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/OpenTodoTxt.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/ProjectsAfter.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/ProjectsBefore.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/ResultGTDSync.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/ScriptingSettings.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/StandardIcons.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/TodoTxtAppSettings.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help/TodoTxtFolderDialog.png"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ContextNa.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ContextVoor.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/DatumFormaat.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/DelegerenNa.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/DelegerenVoor.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/EerstvolgendeActiesNa.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/EerstvolgendeActiesVoor.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/FilterContext.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/FunctieToetsen.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/GebiedenBinnenJeLeven.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/KnoopMetAttributen.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/NaSynchronisatie.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/OpenTodoTxt.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ProjectenNa.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ProjectenVoor.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/RechtenScipts.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ResultatenEersteSynchronisatie.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/ResultatenTweedeSynchronisatie.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/SpecificeerMap.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/StandaardPictogrammen.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/TodoTxtAppInstellingen.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/VerplaatsteNieuweActies.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/VinkEerstvolgendeActieAf.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/VoegEerstvolgendeActieToe.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/doc/_gtdSync_help_nl/Voltooid.PNG"/>
<attribute NAME="delete" VALUE="${installationbase}/src/gtdSync/Help.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/src/gtdSync/NextActionHandler.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/src/gtdSync/Support.groovy"/>
<attribute NAME="delete" VALUE="${installationbase}/resources/images/gtdSyncWithTodoTxt.png"/>
<attribute NAME="delete" VALUE="${installationbase}/resources/images/gtdSyncWithTodoTxt-icon.png"/>
</node>
</node>
</map>
