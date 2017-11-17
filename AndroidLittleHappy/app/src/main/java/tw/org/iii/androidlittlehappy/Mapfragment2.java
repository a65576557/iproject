package tw.org.iii.androidlittlehappy;



import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mapfragment2 extends Fragment implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {

    OnMapfragment2SelectedListener mCallback;


    //public static String activityTitle = "";




    // Container Activity must implement this interface
    public interface OnMapfragment2SelectedListener {
        public void onGpsSelected(double x, double y);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnMapfragment2SelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMapfragment2SelectedListener");
        }
    }

    GoogleMap mMap;
    public Mapfragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.mapfragment2, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setOnInfoWindowClickListener(Mapfragment2.this);


        //ActivityCompat.requestPermissions(this.getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION},12);

        CustomInfoWindowAdapter adapter  = new CustomInfoWindowAdapter(getActivity());
        mMap.setInfoWindowAdapter(adapter);


        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        LatLng III = new LatLng(22.628216, 120.293043);
        LatLng user1 = new LatLng(22.627230, 120.292534);
       // LatLng user2 = new LatLng(gt.getLocation().getLatitude(),gt.getLocation().getLongitude());

       // mMap.addMarker(new MarkerOptions().position(user2).title("user2"));
        /*
        mMap.addMarker(new MarkerOptions().position(III).title("南區資策會")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.penguin));
        mMap.addMarker(new MarkerOptions().position(user1).title("咖啡買一送一")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.coffee));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(III, 18));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user1, 18));*/

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1234);
            //ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1234);


            return;
        }else {

//            if ("".equals(activityTitle)){
//                setupMyLocation();
//            }else{
//                setupMyLocation(activityTitle);
//                activityTitle = "";
//            }

            setupMyLocation();

        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

      /*  AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(marker.getTitle());
        alert.setMessage("ggg");
     alert.setNeutralButton("我有興趣", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialogInterface, int i) {

             Intent intent = new Intent(getActivity(),ActProfile.class);
             startActivity(intent);

         }
     });
        alert.show();       */

      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   /*   LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.actactivitydetail,null);

                    TextView lblMemberName =(TextView)v.findViewById(R.id.lblMemberName);
              lblMemberName.setText("1541161");


                 new  AlertDialog.Builder(getActivity()).setTitle(marker.getTitle()).setView(v).setPositiveButton("我有興趣", new DialogInterface.OnClickListener() {
                     @Override


                     public void onClick(DialogInterface dialogInterface, int i) {

                          Intent intent = new Intent(getActivity(),ActProfile.class);
                                     startActivity(intent);

                     }
                 }).show();*/



      LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.actactivitydetail,null);
        new  AlertDialog.Builder(getActivity()).setTitle(marker.getTitle()).setView(v).show();
         TextView lblCreator =(TextView) v.findViewById(R.id.lblCreator);

lblCreator.setText(marker.getTitle());



       // new ActDialog(getActivity()).imageRes(R.mipmap.ic_launcher).show();





    }
    Marker marker;

    public void setupMyLocation() {




        //noinspection MissingPermission
        mMap.setMyLocationEnabled(true);

        //實作地圖定位按鈕功能
        mMap.setOnMyLocationButtonClickListener(
                new GoogleMap.OnMyLocationButtonClickListener(){
                    @Override
                    public  boolean onMyLocationButtonClick(){
                        GpsTracker gps= new GpsTracker(getActivity());
                        LatLng user3;
                        if(gps.getLocation()!=null) {
                            user3 = new LatLng(gps.getLocation().getLatitude(), gps.getLocation().getLongitude());
                  mMap.addMarker(new MarkerOptions().position(user3).title("ggg")).showInfoWindow();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user3, 16));

                            /*
                            for (int i = 0; i < ActMain.iv_activitylist.size(); i++) {
                                mMap.addMarker(new MarkerOptions().position(new LatLng(ActMain.iv_activitylist.get(i).getGpsX(),ActMain.iv_activitylist.get(i).getGpsY())).title(ActMain.iv_activitylist.get(i).getTitle()));
                                Log.d("test", String.valueOf(ActMain.iv_activitylist.get(i).getGpsX()));
                            }
                            Log.d("test", String.valueOf(ActMain.iv_activitylist.size()));
                            */
                            marker.setTag(1);

                        }
                        return false;
                    }
                }
        );


        GpsTracker gps= new GpsTracker(getActivity());
        LatLng user3;
        if(gps.getLocation()!=null) {
            user3 = new LatLng(gps.getLocation().getLatitude(), gps.getLocation().getLongitude());
              //mMap.addMarker(new MarkerOptions().position(user3).title("123"));
              mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user3, 16));

            //勝文
            mCallback.onGpsSelected(gps.getLocation().getLatitude(), gps.getLocation().getLongitude());

            for (int i = 0; i < ActMain.iv_activitylist.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(ActMain.iv_activitylist.get(i).getGpsX(),ActMain.iv_activitylist.get(i).getGpsY())).title(ActMain.iv_activitylist.get(i).getTitle()));
            }

        }
    }


    private void setupMyLocation(String activityTitle) {
        //noinspection MissingPermission
        mMap.setMyLocationEnabled(true);

        GpsTracker gps= new GpsTracker(getActivity());
        LatLng user3;
        if(gps.getLocation()!=null) {
            user3 = new LatLng(gps.getLocation().getLatitude(), gps.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(user3).title(activityTitle));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user3, 16));


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode){
            case 1234:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

//                    if ("".equals(activityTitle)){
//                        setupMyLocation();
//                    }else{
//                        setupMyLocation(activityTitle);
//                        activityTitle = "";
//                    }

                    setupMyLocation();

                }else {


                }
                break;

        }



        /*
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
                }
            }
        }
        */


    }



}