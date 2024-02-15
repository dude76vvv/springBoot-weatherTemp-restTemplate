import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="text-center">
      <h1 className="text-6xl font-bold pb-2 md:text-8xl">Welcome !</h1>
      <Link to="/map" className="text-2xl hover:underline  md:text-4xl">click here to go to map site</Link>
    </div>
  );
};

export default Home;
