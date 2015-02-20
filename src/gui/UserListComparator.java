/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Comparator;

/**
 *
 * @author Janus
 */
public class UserListComparator implements Comparator<String>
{
    @Override
    public int compare(String user1, String user2)
    {

        int placementPoint1 = player1.getPlacementPoints();
        int placementPoint2 = player2.getPlacementPoints();

        //ascending order
        return placementPoint1 - placementPoint2;

	      //descending order
        //return fruitName2.compareTo(fruitName1);
    }

    @Override
    public int compare(String o1, String o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
