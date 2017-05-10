package com.example.angelo.doorbelliot;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;

/**
 * Created by angelo on 08/05/17.
 */

public class SingletonConnection {

    private static SingletonConnection instance = null;


    private SingletonConnection(Context context){

    }


    public MqttAndroidClient createClient(Context context, String serverName, String clientName){
        MqttAndroidClient client = new MqttAndroidClient(context,serverName,clientName);
        return client;
    }

    public synchronized static SingletonConnection getInstance(Context context)
    {
        if (instance == null) {
            instance = new SingletonConnection(context);
        }

        return instance;
    }


}
