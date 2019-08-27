package com.fei.utils;

import com.fei.domain.Favourite;
import com.fei.domain.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class User_favourite_sort {
    /**
     * 给favourite提供优先级
     * @param favourites
     */
    public static void userFavouriteSort(List<Favourite> favourites){
        List<Favourite> favourites_sort =  favourites;

        Collections.sort(favourites_sort,new Comparator<Favourite>(){            //将favourite置顶
            public int compare(Favourite fav1, Favourite fav2) {
                return fav2.getFlag().compareTo(fav1.getFlag());
            }
        });
    }

    /**
     * 更据Web_App内的信息，往favourite表内分配权限
     * @param favourites
     */
    public static void insertFavouriteSort(List<Favourite> favourites){
        List<Favourite> favourites_sort =  favourites;

        Collections.sort(favourites_sort,new Comparator<Favourite>(){            //将favourite置顶
            public int compare(Favourite fav1, Favourite fav2) {
                return fav2.getFlag().compareTo(fav1.getFlag());
            }
        });
    }
}
