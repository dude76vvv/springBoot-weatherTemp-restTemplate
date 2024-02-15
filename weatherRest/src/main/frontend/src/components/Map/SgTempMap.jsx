import React, { useEffect, useRef, useState } from "react";

import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import { Icon } from "leaflet";
import provider from "../../provider";
import { getAirTemp } from "../../api/api";
import "./sgMap.css";

const SgTempMap = () => {
  const mapRef = useRef(null);

  const LAT = 1.3521;
  const LON = 103.851959;
  const ZOOM = Number(process.env.REACT_APP_ZOOM);
  const FREQ = 3 * 60 * 1000; //min to second to ms

  const [lastUpdated, setlastUpdated] = useState("N/A");
  const [markers, setMarkers] = useState(null);
  const [healthStatus, setHealthStatus] = useState("Down");
  const [myLoc, setmyLoc] = useState(null);
  const [coord, setcoord] = useState(null); //for re-centering

  // const [test, settest] = useState(0)
  // const addTest =()=>{
  //   settest(prev=>prev+1);
  //   console.log(test);}

  //recenter map using child component
  function SetViewOnClick({ coord }) {
    // console.log('from setView');
    // console.log(coord);
    const map = useMap();
    if (coord != null) {
      map.setView(coord, 12);
    }
    return null;
  }

  const reCenter = () => {
    setcoord({
      lon: LON,
      lat: LAT,
    });

    // console.log(coord);
  };

  //update every x mins to get temperature
  useEffect(() => {
    getData();

    const interval = setInterval(() => {
      getData();
    }, FREQ);
    return () => clearInterval(interval);
  }, []);

  const getData = async () => {
    const d = await getAirTemp();

    if (d != null) {
      setMarkers(d["data"]);
      setHealthStatus(d["apiStatus"]);
      setlastUpdated(d.lastUpdated_timeStamp);
      console.log(`api called at ${new Date()}`);
    }
  };

  // console.log(markers);

  const findMe = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      // console.log(position);
      setmyLoc({
        lat: position.coords.latitude,
        lon: position.coords.longitude,
      });
      console.log(myLoc);
      // console.log(myLoc?.lat);
      // console.log(myLoc?.lon);
    });
  };

  const customIcon = new Icon({
    // iconUrl: "https://cdn-icons-png.flaticon.com/512/447/447031.png",
    iconUrl: require("../../images/red_icon.png"),
    iconSize: [38, 38], // size of the icon
  });

  return (
    <div>
      <MapContainer
        center={[LAT, LON]}
        zoom={12}
        ref={mapRef}
        scrollWheelZoom={false}
      >
        {/* <LocationMarker/> */}
        <SetViewOnClick coord={coord} />

        <TileLayer
          url={provider.openStreetMap.url}
          attribution={provider.openStreetMap.attribution}
        />
        {/*iterate to generate the marker  */}

        {markers &&
          markers.map((m) => (
            <Marker key={m.stn_id} position={[m.lat, m.lon]}>
              <Popup>
                <p>
                  Id:<b>{m.stn_id}</b>{" "}
                </p>
                <p>
                  Name:<b>{m.name}</b>
                </p>
                <p>
                  Lat:<b>{m.lat}</b>{" "}
                </p>
                <p>
                  Lon:<b>{m.lon}</b>
                </p>
                <p>
                  Temp:<b>{m.temp}</b>
                </p>
              </Popup>
            </Marker>
          ))}

        {myLoc && (
          <Marker position={[myLoc.lat, myLoc.lon]} icon={customIcon}>
            <Popup>
              <p>I am here</p>
              <p>
                {myLoc.lat},{myLoc.lon}
              </p>
            </Popup>
          </Marker>
        )}
      </MapContainer>

      <div className="my-4 flex items-center">
        <div className="flex-1">
          <div className="flex flex-col items-center md:flex-row ">
            <h3 className="basis-1/4 font-light text-lg">Last updated:</h3>
            <span className="basis-3/4 font-medium flex-1">{lastUpdated}</span>
          </div>

          <div className="flex flex-col items-center md:flex-row ">
            <h3 className="basis-1/4 font-light text-lg">Api status: </h3>
            <span
              className={`basis-3/4 flex-1 font-medium ${
                healthStatus.toLowerCase() === "healthy".toLowerCase()
                  ? "text-green-400"
                  : "text-red-400"
              }`}
            >
              {healthStatus}
            </span>
          </div>
        </div>

        <div className="flex-1 flex flex-col gap-3 items-center md:flex-row">
          <button
            className="w-32 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            onClick={findMe}
          >
            FindMe*
          </button>
          <button
            className="w-32 bg-amber-500 hover:bg-amber-700 text-white font-bold py-2 px-4 rounded"
            onClick={reCenter}
          >
            Re-center
          </button>
          <button
            className="w-32 bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded"
            onClick={getData}
          >
            Refresh
          </button>
        </div>
      </div>

      <div className="mt-2">
        <p>*Allow location access to work</p>
      </div>
    </div>
  );
};

export default SgTempMap;
