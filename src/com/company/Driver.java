package com.company;

import com.company.UI.AdminControlPanel;

public class Driver {

    public static void main (String[] args) {
        //Reference to Admin Control Panel singleton
        AdminControlPanel admin = AdminControlPanel.getInstance();
    }
}
