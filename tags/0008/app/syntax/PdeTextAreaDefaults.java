/* -*- mode: jde; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  PdeTextAreaDefaults - grabs font/color settings for the editor
  Part of the Processing project - http://Proce55ing.net

  Except where noted, code is written by Ben Fry
  Copyright (c) 2001-03 Massachusetts Institute of Technology

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package processing.app.syntax;

import processing.app.*;


public class PdeTextAreaDefaults extends TextAreaDefaults {

  public PdeTextAreaDefaults() {

    inputHandler = new DefaultInputHandler();
    inputHandler.addDefaultKeyBindings();

    // use option on mac for things that are ctrl on windows/linux
    String mod = Base.isMacOS() ? "A" : "C";

    inputHandler.addKeyBinding("S+BACK_SPACE", InputHandler.BACKSPACE);
    inputHandler.addKeyBinding("S+DELETE", InputHandler.DELETE);

    inputHandler.addKeyBinding("BACK_SPACE", InputHandler.BACKSPACE);
    inputHandler.addKeyBinding("C+BACK_SPACE", InputHandler.BACKSPACE_WORD);
    inputHandler.addKeyBinding("DELETE", InputHandler.DELETE);
    inputHandler.addKeyBinding("C+DELETE", InputHandler.DELETE_WORD);

    inputHandler.addKeyBinding("ENTER", InputHandler.INSERT_BREAK);
    inputHandler.addKeyBinding("TAB", InputHandler.INSERT_TAB);

    inputHandler.addKeyBinding("INSERT", InputHandler.OVERWRITE);
    inputHandler.addKeyBinding("C+\\", InputHandler.TOGGLE_RECT);

    // beginning and ending of the current line
    inputHandler.addKeyBinding("HOME", InputHandler.HOME);
    inputHandler.addKeyBinding("END", InputHandler.END);

    if (Base.isMacOS()) {
      inputHandler.addKeyBinding("M+LEFT", InputHandler.HOME);
      inputHandler.addKeyBinding("M+RIGHT", InputHandler.END);
    }

    inputHandler.addKeyBinding("S+HOME", InputHandler.SELECT_HOME);
    inputHandler.addKeyBinding("S+END", InputHandler.SELECT_END);
    inputHandler.addKeyBinding(mod + "+HOME", InputHandler.DOCUMENT_HOME);
    inputHandler.addKeyBinding(mod + "+END", InputHandler.DOCUMENT_END);
    inputHandler.addKeyBinding(mod + "S+HOME", InputHandler.SELECT_DOC_HOME);
    inputHandler.addKeyBinding(mod + "S+END", InputHandler.SELECT_DOC_END);

    inputHandler.addKeyBinding("PAGE_UP", InputHandler.PREV_PAGE);
    inputHandler.addKeyBinding("PAGE_DOWN", InputHandler.NEXT_PAGE);
    inputHandler.addKeyBinding("S+PAGE_UP", InputHandler.SELECT_PREV_PAGE);
    inputHandler.addKeyBinding("S+PAGE_DOWN", InputHandler.SELECT_NEXT_PAGE);

    inputHandler.addKeyBinding("LEFT", InputHandler.PREV_CHAR);
    inputHandler.addKeyBinding("S+LEFT", InputHandler.SELECT_PREV_CHAR);
    inputHandler.addKeyBinding(mod + "+LEFT", InputHandler.PREV_WORD);
    inputHandler.addKeyBinding(mod + "S+LEFT", InputHandler.SELECT_PREV_WORD);
    inputHandler.addKeyBinding("RIGHT", InputHandler.NEXT_CHAR);
    inputHandler.addKeyBinding("S+RIGHT", InputHandler.SELECT_NEXT_CHAR);
    inputHandler.addKeyBinding(mod + "+RIGHT", InputHandler.NEXT_WORD);
    inputHandler.addKeyBinding(mod + "S+RIGHT", InputHandler.SELECT_NEXT_WORD);
    inputHandler.addKeyBinding("UP", InputHandler.PREV_LINE);
    inputHandler.addKeyBinding(mod + "+UP", InputHandler.PREV_LINE);  // p5
    inputHandler.addKeyBinding("S+UP", InputHandler.SELECT_PREV_LINE);
    inputHandler.addKeyBinding("DOWN", InputHandler.NEXT_LINE);
    inputHandler.addKeyBinding(mod + "+DOWN", InputHandler.NEXT_LINE);  // p5
    inputHandler.addKeyBinding("S+DOWN", InputHandler.SELECT_NEXT_LINE);

    inputHandler.addKeyBinding(mod + "+ENTER", InputHandler.REPEAT);

    document = new SyntaxDocument();
    editable = true;
    electricScroll = 3;

    cols = 80;
    rows = 15;


    // moved from SyntaxUtilities
    //DEFAULTS.styles = SyntaxUtilities.getDefaultSyntaxStyles();

    styles = new SyntaxStyle[Token.ID_COUNT];

    // comments
    styles[Token.COMMENT1] = Preferences.getStyle("comment1");
    styles[Token.COMMENT2] = Preferences.getStyle("comment2");

    // abstract, final, private
    styles[Token.KEYWORD1] = Preferences.getStyle("keyword1");

    // beginShape, point, line
    styles[Token.KEYWORD2] = Preferences.getStyle("keyword2");

    // byte, char, short, color
    styles[Token.KEYWORD3] = Preferences.getStyle("keyword3");

    // constants: null, true, this, RGB, TWO_PI
    styles[Token.LITERAL1] = Preferences.getStyle("literal1");

    // p5 built in variables: mouseX, width, pixels
    styles[Token.LITERAL2] = Preferences.getStyle("literal2");

    // ??
    styles[Token.LABEL] = Preferences.getStyle("label");

    // + - = /
    styles[Token.OPERATOR] = Preferences.getStyle("operator");

    // area that's not in use by the text (replaced with tildes)
    styles[Token.INVALID] = Preferences.getStyle("invalid");


    // moved from TextAreaPainter

    font = Preferences.getFont("editor.font");

    fgcolor = Preferences.getColor("editor.fgcolor");
    bgcolor = Preferences.getColor("editor.bgcolor");

    caretVisible = true;
    caretBlinks = Preferences.getBoolean("editor.caret.blink");
    caretColor = Preferences.getColor("editor.caret.color");

    selectionColor = Preferences.getColor("editor.selection.color");

    lineHighlight =
      Preferences.getBoolean("editor.linehighlight");
    lineHighlightColor =
      Preferences.getColor("editor.linehighlight.color");

    bracketHighlight =
      Preferences.getBoolean("editor.brackethighlight");
    bracketHighlightColor =
      Preferences.getColor("editor.brackethighlight.color");

    eolMarkers = Preferences.getBoolean("editor.eolmarkers");
    eolMarkerColor = Preferences.getColor("editor.eolmarkers.color");

    paintInvalid = Preferences.getBoolean("editor.invalid");
  }
}
