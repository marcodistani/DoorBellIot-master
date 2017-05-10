package com.example.angelo.doorbelliot;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity implements NewConnection.PassValues {

    MqttAndroidClient androidClient;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager=(ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public void passage(final String client, final String server, final String topic) {
        androidClient=SingletonConnection.getInstance(getApplicationContext()).createClient(getApplicationContext(),server,client);

        androidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            /*    Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                crono.addMessage("CONNESSIONE PERSA "+cause.getMessage());  */
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d("MESSAGGIO ARRIVATO","ok");
                Bitmap bitmap=DecodeImage.decode(message);
                Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                crono.addMessage(bitmap);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        MqttConnectOptions mqttConnectOptions= new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setKeepAliveInterval(0);

        try {
            androidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    /*Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                    crono.addMessage("CONNESSIONE RIUSCITA AL SERVER "+server);*/
                    sottoscriviTopic(topic);

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                  /*  Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                    crono.addMessage("CONNESSIONE NON RIUSCITA AL SERVER "+exception.getMessage()); */
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
           /* Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
            crono.addMessage("CONNESSIONE NON RIUSCITA "+e.getMessage()); */
        }

    }



    private void sottoscriviTopic(final String topic){
        try {
            androidClient.subscribe(topic, 2, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                  /*  Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                    crono.addMessage("SOTTOSCRIZIONE AL TOPIC "+topic+" RIUSCITA");   */
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                /*    Cronologia crono = (Cronologia) mSectionsPagerAdapter.getItem(1);
                    crono.addMessage("SOTTOSCRIZIONE AL TOPIC "+topic+" NON RIUSCITA"+ exception.getMessage());  */
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
