/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricals;

import static Model.Constants.AC_UNIT_UTIL;

/**
 *
 * @author sabavija
 */
public class AC extends ElecComponent{

    @Override
    public int gettotalpowerutil() {
        return super.isEnabled()? AC_UNIT_UTIL: 0;
    }
    
}
