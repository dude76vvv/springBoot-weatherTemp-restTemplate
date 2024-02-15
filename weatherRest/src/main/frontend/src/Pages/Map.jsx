import React from "react";
import SgTempMap from "../components/Map/SgTempMap";
import { Link } from "react-router-dom";

const Map = () => {
  return (
    <div className="space-y-2 mx-5 min-w-xl md:mx-20 ">
      <h1 className="text-2xl font-medium md:text-4xl">Stns Temp</h1>
      <p className="text-sm font-light">
        Based on:
        <Link
          to="https://api.data.gov.sg/v1/environment/air-temperature"
          target="_blank"
          className="hover:underline"
        >
          {" "}
          https://api.data.gov.sg/v1/environment/air-temperature
        </Link>
      </p>

      {/* 
      <p className="text-sm font-light">
        https://api.data.gov.sg/v1/environment/air-temperature
      </p> */}

      <SgTempMap />
    </div>
  );
};

export default Map;
