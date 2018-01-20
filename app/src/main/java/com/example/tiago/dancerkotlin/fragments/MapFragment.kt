package com.example.tiago.dancerkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Event
import com.example.tiago.dancerkotlin.utils.CustomMapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private var mEvent: Event? = null

    companion object {
        val EXTRA_EVENT = "extra_event"
        fun novaInstancia(event: Event): MapFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENT, event)
            val fragment = MapFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        mEvent = arguments?.getSerializable(EXTRA_EVENT) as Event

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater!!.inflate(R.layout.fragment_map, container, false)
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_establishment_name?.text = mEvent?.addressId?.complement
        txt_establishment_address.text = "${mEvent?.addressId?.street}, ${mEvent?.addressId?.number} - ${mEvent?.addressId?.neighborhood}"
        txt_address_two.text =  "${mEvent?.addressId?.city}, - ${mEvent?.addressId?.state}"

        if (mapView != null) {
            mapView?.onCreate(null)
            mapView?.onResume()
            mapView?.getMapAsync(this)
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(context)
        mGoogleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL



        val lat = mEvent?.addressId?.lat?.toFloat()
        val log = mEvent?.addressId?.long?.toFloat()

        if(lat!=null && log!=null){
            googleMap.addMarker(MarkerOptions().position(LatLng(lat!!.toDouble(), log!!.toDouble())).title(mEvent?.name))
            val cameraPosition = CameraPosition.builder().target(LatLng(lat.toDouble(), log.toDouble())).zoom(16f).bearing(0f).build()
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }




    }


}
