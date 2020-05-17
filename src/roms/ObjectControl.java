/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roms;

import java.util.ArrayList;

/**
 *
 * @author JW Wan
 */
public abstract class ObjectControl <T>{
    abstract ArrayList<T> objectsFromFile();
    abstract void objects2File(ArrayList<T> t);
}
