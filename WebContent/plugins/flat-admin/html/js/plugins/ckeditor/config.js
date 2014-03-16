/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.html or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig=function(config){
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';

    config.toolbar_sylvan=[
	    ['Source','-','Save','NewPage','Preview','-','Templates'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print',
		    'SpellChecker','Scayt'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	    ['Form','Checkbox','Radio','TextField','Textarea','Select',
		    'Button','ImageButton','HiddenField']
	    ];
};
