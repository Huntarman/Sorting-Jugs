package utility;

import jugs.Jugs;

import java.util.*;

public class UtilityFunctions {
    public static LinkedHashMap<Integer, Integer> sortHashMap(HashMap<Integer, Integer> map) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);
        LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (int num : list) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return sortedMap;
    }
    public static ArrayList<Jugs> sortListofJugsbyVolume (ArrayList<Jugs> List){
        ArrayList<Jugs> SortedList = new ArrayList<>();
        SortedList = List;
        Jugs temp;
        for (int i = 0; i < SortedList.size(); i++){
            for (int j = i; j < SortedList.size(); j++){
                if(SortedList.get(i).getVolume() < SortedList.get(j).getVolume()){
                    temp = SortedList.get(i);
                    SortedList.set(i, SortedList.get(j));
                    SortedList.set(j, temp);
                }
            }
        }
        return SortedList;
    }
}
