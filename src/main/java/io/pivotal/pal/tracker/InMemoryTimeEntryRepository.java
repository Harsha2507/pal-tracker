package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long, TimeEntry> resultMap  = new HashMap<>();

    public TimeEntry create(TimeEntry objTimeEntry){

        if(objTimeEntry.getId() == 0){
            Long size = resultMap.size() +1L;
            objTimeEntry.setId(size);
            resultMap.put(size, objTimeEntry);
        }else {
            resultMap.put(objTimeEntry.getId(), objTimeEntry);
        }

        return  objTimeEntry;
    }

    public TimeEntry find(Long id){
        return resultMap.get(id);
    }

    public List<TimeEntry> list(){


        List<TimeEntry> list = new ArrayList<>(resultMap.values());

        return list;
    }

    public TimeEntry update(Long id, TimeEntry objTimeEntry){

        objTimeEntry.setId(id);

        resultMap.put( id, objTimeEntry);

        return resultMap.get(id);
    }

    public void delete(Long id){

        resultMap.remove(id);

    }
}
