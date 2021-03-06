package io.okheart.android.services;


public class ForegroundService {/*extends Service {

    //private String status;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final String TAG = "ForegroundService";
    //public Location alocation;
    //private static LocationManager locationManager;
    //private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 300000;
    //private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 7;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    //private FirebaseFirestore mFirestore;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private Double lat, lng;
    private Float acc;
    private io.okheart.android.database.DataProvider dataProvider;
    //private Query query, queryAlarm;
    private List<Map<String, Object>> addresses;
    //private FirebaseRemoteConfig mFirebaseRemoteConfig;

    private NotificationManager notificationManager;
    //private Boolean firestore;
    private Boolean parsedb;
    //private AlarmManager alarmManager;
    private String uniqueId;
    private Integer remotelocationfrequency;
    private Integer remoteaddressfrequency;
    private Integer remotePingFrequency;
    private Integer remoteresumepingfrequency;
    private Double remotegeosearchradius;
    private Double remotegpsaccuracy;
    private Boolean remotekillswitch;
    private Boolean remoteautostop;
    //private List<io.okheart.android.datamodel.AddressItem> addressItemList;


    public ForegroundService() {

    }

    private static void displayLog(String me) {
        Log.i(TAG, me);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //OkVerifyApplication.setForegroundServiceStatus(false);
        displayLog("foreground service destroyed");

        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "destroy");
            parameters.put("type", "onDestroy");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            //parameters.put("firestore", "" + firestore);
            parameters.put("parse", "" + parsedb);
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }


        try {
            if (notificationManager != null) {
                notificationManager.cancelAll();
            }
        } catch (Exception e) {

        }

        try {
            stopLocationUpdates();
        } catch (Exception e) {

        }
        List<io.okheart.android.datamodel.AddressItem> addressItemList = dataProvider.getAllAddressList();
        if (addressItemList.size() > 0) {
            if (remoteautostop) {
                startAlert(remoteresumepingfrequency);
            } else {
                startAlert(remotePingFrequency);
            }
        } else {
            //maybe an event
        }



        /*
        if(status.equalsIgnoreCase("false")) {
            displayLog("alarm status is false");
            startAlert();
        }
        else{
            displayLog("alarm status is true");
        }
        */

    /*
    }

    public void startAlert(Integer pingTime) {

        /*
        Intent intent = new Intent(this, io.okheart.android.receivers.MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 987623224, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (pingTime),
                pendingIntent);


        try {
            ComponentName receiver = new ComponentName(this, io.okheart.android.receivers.BootReceiver.class);
            PackageManager pm = this.getPackageManager();

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
        } catch (Exception e) {

        }
        */


        /*
        Map<String, Object> city = new HashMap<>();
        city.put("status", "true");
        mFirestore.collection("alarm").document(OkVerifyApplication.getUniqueId())
                .set(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        displayLog("DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayLog("Error writing document"+ e);
                    }
                });
        */

        /*
        displayLog("Alarm set in " + remotePingFrequency + " seconds");


    }

    @Override
    public void onCreate() {
        super.onCreate();
        displayLog("My foreground service onCreate().");
        //firestore = false;
        parsedb = false;
        //Constants.scheduleJob(ForegroundService.this, "Foreground service");

        /*
        try {
            ComponentName receiver = new ComponentName(this, io.okheart.android.receivers.BootReceiver.class);
            PackageManager pm = this.getPackageManager();

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        } catch (Exception e) {

        }
        */
/*
        displayLog("resume_ping_frequency "+ OkHi.getResume_ping_frequency() +" ping_frequency "+ OkHi.getPing_frequency()+
                " background_frequency "+OkHi.getBackground_frequency()+" sms_template "+OkHi.getSms_template()+"" +
                " gps_accuracy "+OkHi.getGps_accuracy()+" kill_switch "+OkHi.getKill_switch());

        remotelocationfrequency = 30000;
        remoteaddressfrequency = 1;
        remotePingFrequency = 900000;
        remoteresumepingfrequency = 900000;
        remotegeosearchradius = 0.1;
        remotegpsaccuracy = 50.0;
        remotekillswitch = false;
        remoteautostop = false;

        uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "create");
            parameters.put("type", "onCreate");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            //parameters.put("branch", "branch");
            //parameters.put("ualId", model.getUalId());
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }
        try {
            //mainActivity = OkVerifyApplication.getMainActivity();
        } catch (Exception e) {
            displayLog("mainactivity is null");
        }
        //mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        /*
        try {
            Double tempFrequency = mFirebaseRemoteConfig.getDouble(REMOTE_BACKGROUND_LOCATION_FREQUENCY);
            remotelocationfrequency = tempFrequency.intValue();
        } catch (Exception e) {
            displayLog("error getting remotelocationfrequency " + e.toString());
        }
        try {
            Double tempPingFrequency = mFirebaseRemoteConfig.getDouble(REMOTE_PING_FREQUENCY);
            remotePingFrequency = tempPingFrequency.intValue();
        } catch (Exception e) {
            displayLog("error getting remotelocationfrequency " + e.toString());
        }
        try {
            Double tempResumePingFrequency = mFirebaseRemoteConfig.getDouble(REMOTE_RESUME_PING_FREQUENCY);
            remoteresumepingfrequency = tempResumePingFrequency.intValue();
        } catch (Exception e) {
            displayLog("error getting remotelocationfrequency " + e.toString());
        }
        try {
            remoteautostop = mFirebaseRemoteConfig.getBoolean(REMOTE_AUTO_STOP);
        } catch (Exception e) {
            displayLog("error getting remoteautostop " + e.toString());
        }
        try {
            remotegpsaccuracy = mFirebaseRemoteConfig.getDouble(REMOTE_GPS_ACCURACY);
        } catch (Exception e) {
            displayLog("error getting remotegpsaccuracy " + e.toString());
        }
        try {
            remotekillswitch = mFirebaseRemoteConfig.getBoolean(REMOTE_KILL_SWITCH);
        } catch (Exception e) {
            displayLog("error getting remotekillswitch " + e.toString());
        }
        try {
            Double tempFrequency = mFirebaseRemoteConfig.getDouble(REMOTE_ADDRESS_FREQUENCY_THRESHOLD);
            remoteaddressfrequency = tempFrequency.intValue();
        } catch (Exception e) {
            displayLog("error getting frequency " + e.toString());
        }
        try {
            remotegeosearchradius = mFirebaseRemoteConfig.getDouble(REMOTE_GEOSEARCH_RADIUS);
        } catch (Exception e) {
            displayLog("error getting frequency " + e.toString());
        }
        */

/*
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        dataProvider = new io.okheart.android.database.DataProvider(io.okheart.android.services.ForegroundService.this);
        //addressItemList = dataProvider.getAllAddressList();

        /*
        mFirestore = FirebaseFirestore.getInstance();
        query = mFirestore.collection("addresses").document(uniqueId)
                .collection("addresses")
                .orderBy("timestamp", Query.Direction.DESCENDING);
        */



/*
        try {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(io.okheart.android.services.ForegroundService.this);
            mSettingsClient = LocationServices.getSettingsClient(io.okheart.android.services.ForegroundService.this);
        } catch (Exception e) {
            displayLog("mfusedlocationclient error " + e.toString());
        }
        /*
        try {
            DocumentReference docRef = mFirestore.collection("alarms").document(OkVerifyApplication.getUniqueId());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            displayLog("DocumentSnapshot data: " + document.getData());
                            status = (String) document.get("status");
                        } else {
                            displayLog( "No such document");

                        }
                    } else {
                        displayLog("get failed with "+ task.getException());
                    }
                }
            });
        }
        catch (Exception e){
            displayLog("error querying firestore for alarm state "+e.toString());
        }
        */


        /*
        try {
            boolean permissionAccessFineLocationApproved =
                    ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED;

            if (permissionAccessFineLocationApproved) {
                try {
                    OkAnalytics okAnalytics = new OkAnalytics();
                    HashMap<String, String> loans = new HashMap<>();
                    //loans.put(PROP_ACTORPHONENUMBER, loginphonenumber);
                    //loans.put(PROP_ACTORNAME, loginname);
                    //loans.put("phonenumber", null);
                    loans.put("type", "Manifest.permission.ACCESS_FINE_LOCATION");
                    okAnalytics.initializeDynamicParameters("app", "permissionAccessFineLocationApproved",
                            "permission", "mainActivityView", null, loans);
                    okAnalytics.sendToAnalytics("hq_okhi", null, null, "okhi");
                } catch (Exception e) {
                    displayLog("event.submit okanalytics error " + e.toString());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    boolean backgroundLocationPermissionApproved =
                            ActivityCompat.checkSelfPermission(ForegroundService.this,
                                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED;

                    if (backgroundLocationPermissionApproved) {
                        // App can access location both in the foreground and in the background.
                        // Start your service that doesn't have a foreground service type
                        // defined.
                        try {
                            OkAnalytics okAnalytics = new OkAnalytics();
                            HashMap<String, String> loans = new HashMap<>();
                            //loans.put(PROP_ACTORPHONENUMBER, loginphonenumber);
                            //loans.put(PROP_ACTORNAME, loginname);
                            //loans.put("phonenumber", null);
                            loans.put("type", "Manifest.permission.ACCESS_FINE_LOCATION");
                            okAnalytics.initializeDynamicParameters("app", "backgroundLocationPermissionApproved",
                                    "permission", "mainActivityView", null, loans);
                            okAnalytics.sendToAnalytics("hq_okhi", null, null, "okhi");
                        } catch (Exception e) {
                            displayLog("event.submit okanalytics error " + e.toString());
                        }
                        //Constants.scheduleJob(MainActivity.this);
                    } else {
                        // App can only access location in the foreground. Display a dialog
                        // warning the user that your app must have all-the-time access to
                        // location in order to function properly. Then, request background
                        // location.
                        try {
                            OkAnalytics okAnalytics = new OkAnalytics();
                            HashMap<String, String> loans = new HashMap<>();
                            //loans.put(PROP_ACTORPHONENUMBER, loginphonenumber);
                            //loans.put(PROP_ACTORNAME, loginname);
                            //loans.put("phonenumber", null);
                            loans.put("type", "Manifest.permission.ACCESS_FINE_LOCATION");
                            okAnalytics.initializeDynamicParameters("app", "backgroundLocationPermissionNotApproved",
                                    "permission", "mainActivityView", null, loans);
                            okAnalytics.sendToAnalytics("hq_okhi", null, null, "okhi");
                        } catch (Exception e) {
                            displayLog("event.submit okanalytics error " + e.toString());
                        }
                        ActivityCompat.requestPermissions(mainActivity, new String[]{
                                        Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                                MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                    }
                } else {

                }
            } else {
                // App doesn't have access to the device's location at all. Make full request
                // for permission.
                try {
                    OkAnalytics okAnalytics = new OkAnalytics();
                    HashMap<String, String> loans = new HashMap<>();
                    //loans.put(PROP_ACTORPHONENUMBER, loginphonenumber);
                    //loans.put(PROP_ACTORNAME, loginname);
                    //loans.put("phonenumber", null);
                    loans.put("type", "Manifest.permission.ACCESS_FINE_LOCATION");
                    okAnalytics.initializeDynamicParameters("app", "permissionAccessFineLocationNotApproved",
                            "permission", "mainActivityView", null, loans);
                    okAnalytics.sendToAnalytics("hq_okhi", null, null, "okhi");
                } catch (Exception e) {
                    displayLog("event.submit okanalytics error " + e.toString());
                }
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ActivityCompat.requestPermissions(mainActivity, new String[]{
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                                },
                                MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                    } else {
                        ActivityCompat.requestPermissions(mainActivity, new String[]{
                                        Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                    }
                } catch (Exception e) {
                    displayLog("Error getting permissions " + e.toString());
                }

            }
        } catch (Exception e) {
            displayLog("error getting permission " + e.toString());
        }
        */

    // Kick off the process of building the LocationCallback, LocationRequest, and
    // LocationSettingsRequest objects.


    /*
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            startForegroundService();
        }
        catch (Exception e){
            displayLog("onstart command error "+e.toString());
        }

        /*
        if (remotekillswitch) {
            displayLog("don't collect data");
            try {
                HashMap<String, String> loans = new HashMap<>();
                //loans.put("phonenumber",postDataParams.get("phone"));
                //loans.put("ualId", model.getUalId());
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "start");
                parameters.put("type", "onStartCommand");
                parameters.put("onObject", "app");
                parameters.put("view", "foregroundService");
                parameters.put("killswitch", "" + remotekillswitch);
                //parameters.put("branch", "branch");
                //parameters.put("ualId", model.getUalId());
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
        } else {
            displayLog("please collect data");
            try {
                HashMap<String, String> loans = new HashMap<>();
                //loans.put("phonenumber",postDataParams.get("phone"));
                //loans.put("ualId", model.getUalId());
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "start");
                parameters.put("type", "onStartCommand");
                parameters.put("onObject", "app");
                parameters.put("view", "foregroundService");
                parameters.put("killswitch", "" + remotekillswitch);
                //parameters.put("ualId", model.getUalId());
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
            startForegroundService();
        }
        */
/*
        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "start");
            parameters.put("type", "onStartCommand");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            //parameters.put("killswitch", "" + remotekillswitch);
            //parameters.put("ualId", model.getUalId());
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }


        return super.onStartCommand(intent, flags, startId);
    }

    /* Used to build and start foreground service. */

/*
    private void startForegroundService() {

        if (notificationManager != null) {
            displayLog("notification is not null");
            notificationManager.cancelAll();
        } else {
            displayLog("notification is null");
        }

        displayLog("Start foreground service.");
        Bitmap largeIconBitmap = BitmapFactory.decodeResource(getResources(), io.okheart.android.R.drawable.ic_launcher_foreground);
        /*
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Address verification service.");
        bigTextStyle.bigText("Proof of address powered by OkHi");
        */

/*

        Intent playIntent = new Intent(this, ForegroundService.class);
        playIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_ONE_SHOT);
        //NotificationCompat.Action playAction = new NotificationCompat.Action(android.R.drawable.ic_media_play, "Play", pendingIntent);

        String channelId = getString(io.okheart.android.R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(io.okheart.android.R.drawable.ic_stat_ic_notification)
                        .setContentTitle("OkVerify")
                        .setContentText("Location verification in progress")
                        .setAutoCancel(true)
                        .setLargeIcon(largeIconBitmap)
                        .setPriority(IMPORTANCE_LOW)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setWhen(System.currentTimeMillis())
                        //.setSound(defaultSoundUri)
                        //.addAction(playAction)
                        .setFullScreenIntent(pendingIntent, false)
                        //.setStyle(bigTextStyle)
                        .setContentIntent(pendingIntent);


        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

        }
        Notification notification = notificationBuilder.build();


        notificationManager.notify(1, notification);

        // Start foreground service.
        startForeground(1, notification);

        if (remoteautostop) {
            stopSelf(true);

        } else {
            startLocationUpdates();
        }


    }

    private void stopLocationUpdates() {
        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "stop");
            parameters.put("type", "stopLocationUpdates");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }
        try {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        } catch (Exception e) {

        }
    }

    private void startLocationUpdates() {
        displayLog("startLocationUpdates");
        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "start");
            parameters.put("type", "startLocationUpdates");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            parameters.put("killswitch", "" + remotekillswitch);
            //parameters.put("ualId", model.getUalId());
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }
        try {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, null)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            displayLog("addOnCompleteListener 1 ");
                            try {
                                HashMap<String, String> loans = new HashMap<>();
                                loans.put("uniqueId", uniqueId);
                                HashMap<String, String> parameters = new HashMap<>();
                                parameters.put("eventName", "Data Collection Service");
                                parameters.put("subtype", "complete");
                                parameters.put("type", "startLocationUpdates");
                                parameters.put("onObject", "app");
                                parameters.put("view", "foregroundService");
                                parameters.put("killswitch", "" + remotekillswitch);
                                //parameters.put("ualId", model.getUalId());
                                sendEvent(parameters, loans);
                            } catch (Exception e1) {
                                displayLog("error attaching afl to ual " + e1.toString());
                            }
                            //sendSMS("location callback complete");
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    displayLog("addOnSuccessListener 3");
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "success");
                        parameters.put("type", "startLocationUpdates");
                        parameters.put("onObject", "app");
                        parameters.put("view", "foregroundService");
                        parameters.put("killswitch", "" + remotekillswitch);
                        //parameters.put("ualId", model.getUalId());
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    //sendSMS("location callback success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    displayLog("addOnFailureListener 2 " + e.getMessage());
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "failure");
                        parameters.put("type", "startLocationUpdates");
                        parameters.put("onObject", "app");
                        parameters.put("view", "foregroundService");
                        parameters.put("killswitch", "" + remotekillswitch);
                        parameters.put("error", e.getMessage());
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    sendSMS("location callback failure");
                    startAlert(remotePingFrequency);

                }
            });
        } catch (SecurityException e) {
            displayLog("requestLocationUpdates error " + e.toString());
            //displayToast("Please enable GPS location", true);
            sendSMS("location callback security exception");
        }


    }

    private void createLocationRequest() {

        displayLog("frequency " + remotelocationfrequency);
        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "create");
            parameters.put("type", "createLocationRequest");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(remotelocationfrequency);
        mLocationRequest.setFastestInterval(remotelocationfrequency / 2);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void createLocationCallback() {
        displayLog("create location callback");

        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "create");
            parameters.put("type", "createLocationCallback");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if (locationResult == null) {
                    displayLog("lat cannot get location");
                    sendSMS("with null location");
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "result");
                        parameters.put("type", "onLocationResult");
                        parameters.put("onObject", "null");
                        parameters.put("view", "foregroundService");
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    return;
                } else {
                    mCurrentLocation = locationResult.getLastLocation();
                    lat = mCurrentLocation.getLatitude();
                    lng = mCurrentLocation.getLongitude();
                    acc = mCurrentLocation.getAccuracy();

                    displayLog("lat " + lat + " lng " + lng + " acc " + acc);

                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "result");
                        parameters.put("type", "onLocationResult");
                        parameters.put("onObject", "app");
                        parameters.put("view", "foregroundService");
                        parameters.put("latitude", "" + lat);
                        parameters.put("longitude", "" + lng);
                        parameters.put("gpsAccuracy", "" + acc);
                        parameters.put("remoteGPSAccuracy", "" + remotegpsaccuracy);
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }

                    if (acc > remotegpsaccuracy) {
                        displayLog("lat quick");
                        //Constants.scheduleQuickJob(ForegroundService.this);
                        sendSMS("without acc location " + acc);
                    } else {
                        displayLog("lat updatedatabase");
                        sendSMS("with acc location");
                        updateDatabase(lat, lng, acc);
                        //stopSelf();
                    }
                }
            }
        };

    }

    private void buildLocationSettingsRequest() {
        displayLog("buildLocationSettingsRequest");
        try {
            HashMap<String, String> loans = new HashMap<>();
            loans.put("uniqueId", uniqueId);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("eventName", "Data Collection Service");
            parameters.put("subtype", "create");
            parameters.put("type", "buildLocationSettingsRequest");
            parameters.put("onObject", "app");
            parameters.put("view", "foregroundService");
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        builder.setNeedBle(true);
        mLocationSettingsRequest = builder.build();

    }

    private void sendSMS(String who) {
        /*
        try {
            //String message = "https://hypertrack-996a0.firebaseapp.com/?id="+OkVerifyApplication.getUniqueId();
            //String message = remoteSmsTemplate + OkVerifyApplication.getUniqueId();
            final HashMap<String, String> jsonObject = new HashMap<>();
            jsonObject.put("userId", "GrlaR3LHUP");
            jsonObject.put("sessionToken", "r:3af107bf99e4c6f2a91e6fec046f5fc7");
            jsonObject.put(COLUMN_BRANCH, "hq_okhi");
            jsonObject.put(COLUMN_AFFILIATION, "okhi");
            jsonObject.put("customName", "test");
            //jsonObject.put("ualId", verifyDataItem.getUalId());
            jsonObject.put("phoneNumber", "+254713567907");
            jsonObject.put("phone", "+254713567907");
            jsonObject.put("message", "we have received "+getDeviceModelAndBrand()+" status "+who);
            SendCustomLinkSmsCallBack sendCustomLinkSmsCallBack = new SendCustomLinkSmsCallBack() {
                @Override
                public void querycomplete(String response, boolean status) {
                    if (status) {
                        displayLog("send sms success " + response);
                        //displayToast("SMS sent", true);
                    } else {
                        displayLog("send sms failure " + response);
                        //displayToast("Error " + response, true);
                    }
                }
            };
            SendCustomLinkSmsTask sendCustomLinkSmsTask = new SendCustomLinkSmsTask(sendCustomLinkSmsCallBack, jsonObject);
            sendCustomLinkSmsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch (Exception jse) {
            displayLog("jsonexception " + jse.toString());
        }
        */


/*
    }

    private void firebase(String who) {

        //final Long timemilliseconds = System.currentTimeMillis();

        /*
        mFirestore.collection("verifydata").document("data")
                .collection(uniqueId).document("" + timemilliseconds)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        displayLog("Documentsnapshot successfully written!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayLog("Error writing document " + e.toString());

                    }
                });
        */


/*
    }


    private void updateDatabase(final Double lat, final Double lng, final Float acc) {
        addresses = new ArrayList<>();

        try {
            stopLocationUpdates();
        } catch (Exception e) {
            displayLog("Error stopping location update " + e.toString());
        }


        HashMap<String, String> loans = new HashMap<>();
        loans.put("uniqueId", uniqueId);
        HashMap<String, String> parameters = new HashMap<>();

        final Long timemilliseconds = System.currentTimeMillis();

        final ParseObject parseObject = new ParseObject("UserVerificationData");

        parseObject.put("latitude", lat);
        parseObject.put("longitude", lng);
        parseObject.put("gpsAccuracy", acc);
        ParseGeoPoint parseGeoPoint = new ParseGeoPoint(lat, lng);
        parseObject.put("geoPoint", parseGeoPoint);
        parseObject.put("geoPointSource", "clientBackgroundGPS");
        parseObject.put("timemilliseconds", timemilliseconds);
        parseObject.put("device", getDeviceModelAndBrand());
        parseObject.put("model", Build.MODEL);
        parseObject.put("brand", Build.MANUFACTURER);
        parseObject.put("OSVersion", Build.VERSION.SDK_INT);
        parseObject.put("OSName", "Android");
        parseObject.put("appVersionCode", io.okheart.android.BuildConfig.VERSION_CODE);
        parseObject.put("appVersionName", io.okheart.android.BuildConfig.VERSION_NAME);

        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiManager.getConnectionInfo();
            String ssid = info.getSSID();
            displayLog("ssid " + ssid);
            if (ssid.contains("unknown")) {

            } else {
                if (ssid.length() > 0) {
                    displayLog("ssid " + ssid.substring(1, ssid.length() - 1));
                    parameters.put("ssid", ssid);
                    parseObject.put("ssid", ssid);
                } else {

                }
            }


            try {
                List<String> configuredSSIDList = new ArrayList<>();
                //List<String> scannedSSIDList = new ArrayList<>();
                List<WifiConfiguration> configuredList = wifiManager.getConfiguredNetworks();
                //List<ScanResult> scanResultList = wifiManager.getScanResults();
                //displayLog("configured list size "+configuredList.size());
                //displayLog("scanned list size "+scanResultList.size());
                for (WifiConfiguration config : configuredList) {
                    //displayLog("configured list "+config.SSID);
                    configuredSSIDList.add(config.SSID);
                }
                if (configuredSSIDList != null) {
                    if (configuredSSIDList.size() > 0) {
                        parameters.put("configuredSSIDs", configuredSSIDList.toString());
                    }
                }
                parseObject.put("configuredSSIDs", configuredSSIDList);
                /*
                for(ScanResult config : scanResultList) {
                    displayLog("scanned list "+config.SSID);
                    scannedSSIDList.add(config.SSID);
                }
                */


/*
            } catch (Exception e) {
                displayLog("error gettign scanned list " + e.toString());
            }


        } catch (Exception e) {
            displayLog(" error getting wifi info " + e.toString());
        }


        try {
            boolean isPlugged = false;
            BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
            int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            parameters.put("batteryLevel", "" + batLevel);
            parseObject.put("batteryLevel", batLevel);

            Intent intent = ForegroundService.this.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            isPlugged = plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                isPlugged = isPlugged || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS;
            }
            parameters.put("isPlugged", "" + isPlugged);
            parseObject.put("isPlugged", isPlugged);

            // Are we charging / charged?
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            parameters.put("isCharging", "" + isCharging);
            parseObject.put("isCharging", isCharging);

            // How are we charging?
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            parameters.put("usbCharge", "" + usbCharge);
            parseObject.put("usbCharge", usbCharge);
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            parameters.put("acCharge", "" + acCharge);
            parseObject.put("acCharge", acCharge);

        } catch (Exception e) {
            displayLog(" error getting battery status " + e.toString());
        }

        parseObject.put("uniqueId", uniqueId);
        parameters.put("uniqueId", uniqueId);

        try {

            parameters.put("uniqueId", uniqueId);
            parameters.put("cookieToken", uniqueId);
            parameters.put("eventName", "Data collection Service");
            parameters.put("subtype", "saveBackgroundData");
            parameters.put("type", "saveData");
            parameters.put("onObject", "backgroundService");
            parameters.put("view", "foregroundService");
            parameters.put("branch", "hq_okhi");
            //parameters.put("deliveryId", null);
            //parameters.put("ualId", addressParseObject.getClaimUalId());
            parameters.put("userAffiliation", "okhi");

            parameters.put("latitude", "" + lat);
            parameters.put("longitude", "" + lng);
            parameters.put("gpsAccuracy", "" + acc);
            try {
                Location location2 = new Location("geohash");
                location2.setLatitude(lat);
                location2.setLongitude(lng);

                io.okheart.android.utilities.geohash.GeoHash hash = io.okheart.android.utilities.geohash.GeoHash.fromLocation(location2, 12);
                parameters.put("location", hash.toString());
            } catch (Exception e) {
                displayLog("geomap error " + e.toString());
            }
            parameters.put("geoPointSource", "clientBackgroundGPS");
            parameters.put("timemilliseconds", "" + timemilliseconds);
            parameters.put("device", getDeviceModelAndBrand());
            parameters.put("model", Build.MODEL);
            parameters.put("brand", Build.MANUFACTURER);
            parameters.put("OSVersion", "" + Build.VERSION.SDK_INT);
            parameters.put("OSName", "Android");
            parameters.put("appVersionCode", "" + io.okheart.android.BuildConfig.VERSION_CODE);
            parameters.put("appVersionName", "" + io.okheart.android.BuildConfig.VERSION_NAME);
            sendEvent(parameters, loans);
        } catch (Exception e1) {
            displayLog("error attaching afl to ual " + e1.toString());
        }

        List<io.okheart.android.datamodel.AddressItem> addressItemList = dataProvider.getAllAddressList();

        if (addressItemList.size() > 0) {
            for (int i = 0; i < addressItemList.size(); i++) {
                try {
                    io.okheart.android.datamodel.AddressItem addressItem = addressItemList.get(i);
                    Float distance = getDistance(lat, lng, addressItem.getLat(), addressItem.getLng());
                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("ualId", addressItem.getUalid());
                    nestedData.put("latitude", addressItem.getLat());
                    nestedData.put("longitude", addressItem.getLng());
                    if (distance < 100.0) {
                        nestedData.put("verified", true);
                    } else {
                        nestedData.put("verified", false);
                    }

                    nestedData.put("distance", distance);
                    HashMap<String, String> paramText = getTitleText(addressItem);
                    nestedData.put("title", paramText.get("header"));
                    nestedData.put("text", paramText.get("text"));
                    addresses.add(nestedData);
                } catch (Exception e) {

                }
            }
            parseObject.put("addresses", addresses);
            saveData(parseObject);
        } else {
            //add an event here saying we don't have addresses
            //saveData(parseObject,  timemilliseconds);
            stopSelf();
        }

    }


    private void saveData(ParseObject parseObject) {

        displayLog("parse object save");
        parseObject.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    displayLog("save parseobject success ");
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "saveData");
                        parameters.put("type", "parse");
                        parameters.put("onObject", "success");
                        parameters.put("view", "foregroundService");
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    parsedb = true;
                    stopSelf(parsedb);

                } else {
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "saveData");
                        parameters.put("type", "parse");
                        parameters.put("onObject", "failure");
                        parameters.put("view", "foregroundService");
                        parameters.put("error", e.toString());
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    displayLog("save parseobject error " + e.toString());
                    parsedb = true;
                    stopSelf(parsedb);
                }
            }
        });


    }

    /*
    private void saveData(ParseObject parseObject, Map<String, Object> user, Long timemilliseconds) {
        displayLog("about to saveData");


        mFirestore.collection("verifydata").document("data")
                .collection(uniqueId).document("" + timemilliseconds)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        displayLog("Documentsnapshot successfully written!");
                        try {
                            HashMap<String, String> loans = new HashMap<>();
                            loans.put("uniqueId", uniqueId);
                            HashMap<String, String> parameters = new HashMap<>();
                            parameters.put("eventName", "Data Collection Service");
                            parameters.put("subtype", "saveData");
                            parameters.put("type", "firestore");
                            parameters.put("onObject", "success");
                            parameters.put("view", "foregroundService");
                            sendEvent(parameters, loans);
                        } catch (Exception e1) {
                            displayLog("error attaching afl to ual " + e1.toString());
                        }

                        firestore = true;
                        stopSelf(firestore, parsedb);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayLog("Error writing document " + e.toString());
                        try {
                            HashMap<String, String> loans = new HashMap<>();
                            loans.put("uniqueId", uniqueId);
                            HashMap<String, String> parameters = new HashMap<>();
                            parameters.put("eventName", "Data Collection Service");
                            parameters.put("subtype", "saveData");
                            parameters.put("type", "firestore");
                            parameters.put("onObject", "failed");
                            parameters.put("view", "foregroundService");
                            sendEvent(parameters, loans);
                        } catch (Exception e1) {
                            displayLog("error attaching afl to ual " + e1.toString());
                        }

                        //firestore = true;
                        stopSelf(firestore, parsedb);
                    }
                });


        displayLog("parse object save");
        parseObject.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    displayLog("save parseobject success ");
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "saveData");
                        parameters.put("type", "parse");
                        parameters.put("onObject", "success");
                        parameters.put("view", "foregroundService");
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }

                    parsedb = true;
                    stopSelf(firestore, parsedb);

                } else {
                    try {
                        HashMap<String, String> loans = new HashMap<>();
                        loans.put("uniqueId", uniqueId);
                        HashMap<String, String> parameters = new HashMap<>();
                        parameters.put("eventName", "Data Collection Service");
                        parameters.put("subtype", "saveData");
                        parameters.put("type", "parse");
                        parameters.put("onObject", "failure");
                        parameters.put("view", "foregroundService");
                        parameters.put("error", e.toString());
                        sendEvent(parameters, loans);
                    } catch (Exception e1) {
                        displayLog("error attaching afl to ual " + e1.toString());
                    }
                    displayLog("save parseobject error " + e.toString());

                    parsedb = true;
                    stopSelf(firestore, parsedb);
                }
            }
        });
    }
    */

    /*
    private Boolean addressVerified(VerifyDataItem verifyDataItem) {
        displayLog("geosearch radius " + remotegeosearchradius + " address frequency " + remoteaddressfrequency);
        displayLog("ualid " + verifyDataItem.getUalId() + " lat " + verifyDataItem.getLatitude() + " lng " + verifyDataItem.getLongitude());
        Boolean results = false;
        if (verifyDataItem != null) {
            Double lat = verifyDataItem.getLatitude();
            Double lng = verifyDataItem.getLongitude();
            if (lat != null) {
                CollectionReference collectionReference = mFirestore.collection("verifydata")
                        .document("data")
                        .collection(OkVerifyApplication.getUniqueId());
                GeoFirestore geoFirestore = new GeoFirestore(collectionReference);
                GeoQuery geoQuery = geoFirestore.queryAtLocation(new GeoPoint(lat, lng), remotegeosearchradius);
                ArrayList<Query> queryArrayList = geoQuery.getQueries();
                displayLog("geo query size " + queryArrayList.size());
                if (queryArrayList != null) {
                    if (queryArrayList.size() >= remoteaddressfrequency) {
                        results = true;
                    }
                }
            } else {
                displayLog("verify data lat is null");
            }
        } else {
            displayLog("verify data item is null");
        }
        displayLog("returning " + results);
        return results;
    }
    */


    /*
    private void stopSelf(Boolean parsedb) {
        displayLog("stopself parse " + parsedb);

        if (parsedb) {
            displayLog("if stop self");
            try {
                HashMap<String, String> loans = new HashMap<>();
                loans.put("uniqueId", uniqueId);
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "stop");
                parameters.put("type", "stopSelf");
                parameters.put("onObject", "stopped");
                parameters.put("view", "foregroundService");
                parameters.put("parse", "" + parsedb);
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
            stopSelf();
        } else {
            displayLog("else stop self");
            try {
                HashMap<String, String> loans = new HashMap<>();
                loans.put("uniqueId", uniqueId);
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "stop");
                parameters.put("type", "stopSelf");
                parameters.put("onObject", "notStopped");
                parameters.put("view", "foregroundService");
                parameters.put("parse", "" + parsedb);
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
        }

    }

    /*
    private void stopSelf(Boolean firestore, Boolean parsedb) {
        displayLog("stopself firestore " + firestore + " parse " + parsedb);

        if (firestore && parsedb) {
            displayLog("if stop self");
            try {
                HashMap<String, String> loans = new HashMap<>();
                loans.put("uniqueId", uniqueId);
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "stop");
                parameters.put("type", "stopSelf");
                parameters.put("onObject", "stopped");
                parameters.put("view", "foregroundService");
                parameters.put("firestore", "" + firestore);
                parameters.put("parse", "" + parsedb);
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
            stopSelf();
        } else {
            displayLog("else stop self");
            try {
                HashMap<String, String> loans = new HashMap<>();
                loans.put("uniqueId", uniqueId);
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("eventName", "Data Collection Service");
                parameters.put("subtype", "stop");
                parameters.put("type", "stopSelf");
                parameters.put("onObject", "notStopped");
                parameters.put("view", "foregroundService");
                parameters.put("firestore", "" + firestore);
                parameters.put("parse", "" + parsedb);
                sendEvent(parameters, loans);
            } catch (Exception e1) {
                displayLog("error attaching afl to ual " + e1.toString());
            }
        }

    }
    */
/*
    private Float getDistance(Double latA, Double lngA, Double latB, Double lngB) {

        Location locationA = new Location("point A");

        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB = new Location("point B");

        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);

        Float me = locationA.distanceTo(locationB);
        displayLog("getDistance " + latA + " " + lngA + " " + latB + " " + lngB + " distance " + me);
        return me;

    }

    private String getDeviceModelAndBrand() {

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.contains(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }

    }

    private HashMap<String, String> getTitleText(io.okheart.android.datamodel.AddressItem model) {

        String streetName = model.getStreetName();
        String propertyName = model.getPropname();
        String directions = model.getDirection();
        String title = model.getLocationName();

        displayLog(streetName + " " + propertyName + " " + directions + " " + title);

        HashMap<String, String> titleText = new HashMap<>();

        String header = "";
        String text = "";
        if (streetName != null) {
            if (streetName.length() > 0) {
                if (!(streetName.equalsIgnoreCase("null"))) {
                    text = streetName;
                } else {

                    if (directions != null) {
                        if (directions.length() > 0) {
                            if (!(directions.equalsIgnoreCase("null"))) {
                                text = directions;
                            }
                        }
                    }
                }
            } else {
                if (directions != null) {
                    if (directions.length() > 0) {
                        if (!(directions.equalsIgnoreCase("null"))) {
                            text = directions;
                        }
                    }
                }
            }
        } else {
            if (directions != null) {
                if (directions.length() > 0) {
                    if (!(directions.equalsIgnoreCase("null"))) {
                        text = directions;
                    }
                }
            }
        }

        if (title != null) {
            if (title.length() > 0) {
                if (!(title.equalsIgnoreCase("null"))) {
                    header = title;
                } else {

                    if (propertyName != null) {
                        if (propertyName.length() > 0) {
                            if (!(propertyName.equalsIgnoreCase("null"))) {
                                header = propertyName;
                            }
                        }
                    }
                }
            } else {
                if (propertyName != null) {
                    if (propertyName.length() > 0) {
                        if (!(propertyName.equalsIgnoreCase("null"))) {
                            header = propertyName;
                        }
                    }
                }
            }
        } else {
            if (propertyName != null) {
                if (propertyName.length() > 0) {
                    if (!(propertyName.equalsIgnoreCase("null"))) {
                        header = propertyName;
                    }
                }
            }
        }
        titleText.put("header", header);
        titleText.put("text", text);
        displayLog("titletext " + titleText.get("header") + " " + titleText.get("text"));
        return titleText;
    }

    private HashMap<String, String> getTitleText(io.okheart.android.datamodel.VerifyDataItem model) {

        String streetName = model.getStreetName();
        String propertyName = model.getPropertyName();
        String directions = model.getDirections();
        String title = model.getTitle();

        displayLog(streetName + " " + propertyName + " " + directions + " " + title);

        HashMap<String, String> titleText = new HashMap<>();

        String header = "";
        String text = "";
        if (streetName != null) {
            if (streetName.length() > 0) {
                if (!(streetName.equalsIgnoreCase("null"))) {
                    text = streetName;
                } else {

                    if (directions != null) {
                        if (directions.length() > 0) {
                            if (!(directions.equalsIgnoreCase("null"))) {
                                text = directions;
                            }
                        }
                    }
                }
            } else {
                if (directions != null) {
                    if (directions.length() > 0) {
                        if (!(directions.equalsIgnoreCase("null"))) {
                            text = directions;
                        }
                    }
                }
            }
        } else {
            if (directions != null) {
                if (directions.length() > 0) {
                    if (!(directions.equalsIgnoreCase("null"))) {
                        text = directions;
                    }
                }
            }
        }

        if (title != null) {
            if (title.length() > 0) {
                if (!(title.equalsIgnoreCase("null"))) {
                    header = title;
                } else {

                    if (propertyName != null) {
                        if (propertyName.length() > 0) {
                            if (!(propertyName.equalsIgnoreCase("null"))) {
                                header = propertyName;
                            }
                        }
                    }
                }
            } else {
                if (propertyName != null) {
                    if (propertyName.length() > 0) {
                        if (!(propertyName.equalsIgnoreCase("null"))) {
                            header = propertyName;
                        }
                    }
                }
            }
        } else {
            if (propertyName != null) {
                if (propertyName.length() > 0) {
                    if (!(propertyName.equalsIgnoreCase("null"))) {
                        header = propertyName;
                    }
                }
            }
        }
        titleText.put("header", header);
        titleText.put("text", text);
        displayLog("titletext " + titleText.get("header") + " " + titleText.get("text"));
        return titleText;
    }

    private void sendEvent(HashMap<String, String> parameters, HashMap<String, String> loans) {
        try {
            io.okheart.android.utilities.OkAnalytics okAnalytics = new io.okheart.android.utilities.OkAnalytics(ForegroundService.this);
            okAnalytics.sendToAnalytics(parameters, loans);
        } catch (Exception e) {
            displayLog("error sending photoexpanded analytics event " + e.toString());
        }
    }

    private void displayToast(String msg, boolean show) {
        if (show) {
            try {
                Toast toast = Toast.makeText(ForegroundService.this,
                        msg, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } catch (Exception e) {
                displayLog("Enable data toast error " + e.toString());
            }
        }
    }
    */
}
