package selmibenromdhane.sparta_v1.services;

import android.content.Context;
;

public class getLocation  {




	/**
	 * A placeholder fragment containing a simple view.
	 */




	public static String getLocation(Context context)
	{
		String address = "";
		GPSService mGPSService = new GPSService(context);
		mGPSService.getLocation();

		if (mGPSService.isLocationAvailable == false) {

			// Here you can ask the user to try again, using return; for that
			//Toast.makeText(getActivity(), "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
			return "";

			// Or you can continue without getting the location, remove the return; above and uncomment the line given below
			// address = "Location not available";
		} else {

			// Getting location co-ordinates
			double latitude = mGPSService.getLatitude();
			double longitude = mGPSService.getLongitude();
		//	Toast.makeText(getActivity(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();
			System.out.println(latitude +"--"+longitude);
			address = mGPSService.getLocationAddress();
			//String[] parts = address.split(",");
		//	address= parts[0]; // 004
	//		tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
	//		tvAddress.setText("Address: " + address);
		}


		// make sure you close the gps after using it. Save user's battery power
		mGPSService.closeGPS();
		return address;
		//return address;
	}

}
