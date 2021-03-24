package com.minealert.alert;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class MineAlertDataTypes {

    public static Map<String, Integer> coalMined = Maps.newConcurrentMap();

    public static Map<String, Integer> ironMined = Maps.newConcurrentMap();

    public static Map<String, Integer> goldMined = Maps.newConcurrentMap();

    public static Map<String, Integer> lapisMined = Maps.newConcurrentMap();

    public static Map<String, Integer> redstoneMined = Maps.newConcurrentMap();

    public static Map<String, Integer> diamondsMined = Maps.newConcurrentMap();

    public static Map<String, Integer> emeraldsMined = Maps.newConcurrentMap();

    public static Map<String, Integer> ancientdebrisMined = Maps.newConcurrentMap();

    public static Map<String, Integer> nethergoldMined = Maps.newConcurrentMap();

    public static Map<String, Integer> spawnersMined = Maps.newConcurrentMap();

    public static Map<String, Boolean> alert = Maps.newConcurrentMap();
}
