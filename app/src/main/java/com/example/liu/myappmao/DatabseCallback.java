package com.example.liu.myappmao;

import java.util.List;

public interface DatabseCallback  {


    void setData(onDatabaseCallbacak callbacak);

    interface onDatabaseCallbacak {
            void getData(List list);

        }

}
