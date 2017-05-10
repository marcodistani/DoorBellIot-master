package com.example.angelo.doorbelliot;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;

/**
 * Created by angelo on 08/05/17.
 */

public class NewConnection extends android.support.v4.app.Fragment {
    private EditText client, server, port, topic;
    private Button conn;
    String clientName,serverName, topicName;
    int portName;
    PassValues pass;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view= inflater.inflate(R.layout.new_connection, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        client=(EditText)view.findViewById(R.id.clientId);
        server=(EditText)view.findViewById(R.id.serverURI);
        port=(EditText)view.findViewById(R.id.port);
        topic=(EditText)view.findViewById(R.id.sub);
        conn=(Button)view.findViewById(R.id.newConnection);


        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parseValori(client.getText().toString(),server.getText().toString(),port.getText().toString(),topic.getText().toString())){
                    clientName=client.getText().toString();
                    serverName=server.getText().toString();
                    portName=Integer.parseInt(port.getText().toString());
                    topicName=topic.getText().toString();
                    serverName="tcp://"+serverName+":"+portName;

                    pass.passage(clientName,serverName,topicName);
                }else Toast.makeText(getContext(),"inserire campi mancanti",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean parseValori(String client, String server, String port, String topic) {
        boolean ok=false;
        if(client.equals("") || server.equals("")  || port.equals("") || topic.equals("")){
        }else ok=true;
        return ok;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(getContext());
        pass=(PassValues)context;
    }

    public interface PassValues{
        public void passage (String client, String server,String topic);
    }
}
