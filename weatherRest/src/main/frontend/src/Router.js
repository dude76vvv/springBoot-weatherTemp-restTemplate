import { createBrowserRouter } from "react-router-dom";
import Root from "./Pages/Root";
import Home from "./Pages/Home";
import Layout1 from "./Pages/Layout1";
import Layout2 from "./Pages/Layout2";

import Map from "./Pages/Map";
import Notfound from "./Pages/Notfound";

//for mapping the components to url path

export const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,                            //entry point for routing
      errorElement:<Notfound/>,
      children: [
        { path: "", element: <Layout1 /> },     
        { path: "home", element: <Layout1 /> },     //will render Home Component
        { path: "map", 
          element: <Layout2 /> ,                    
          children: [
            { path: "", element: <Map/> },          //will render the SgMapComponnet
          ],
        },
      ],
    },
  ]);