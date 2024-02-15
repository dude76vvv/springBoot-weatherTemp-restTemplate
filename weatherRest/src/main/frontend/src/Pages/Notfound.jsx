import React from "react";
import { Link } from "react-router-dom";

const Notfound = () => {
  return (
    <div className="max-w-md">
      <h1 className="text-6xl font-bold pb-2 md:text-8xl">404 Not found</h1>
      <Link className="text-2xl hover:underline md:text-4xl" to="/">
        click here to go to home page
      </Link>
    </div>
  );
};

export default Notfound;
