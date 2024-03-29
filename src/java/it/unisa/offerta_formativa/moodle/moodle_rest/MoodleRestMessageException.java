/*
 *  Copyright (C) 2012 Bill Antonia
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package it.unisa.offerta_formativa.moodle.moodle_rest;

/**
 *
 * @author Bill Antonia
 */
public class MoodleRestMessageException extends MoodleRestException {

    /**
     *
     */
    public static final String NO_RECIPIENT="Recipient id is required";
    /**
     *
     */
    public static final String NO_MESSAGE="Message text is required";
    
    MoodleRestMessageException() {}
    
    MoodleRestMessageException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
