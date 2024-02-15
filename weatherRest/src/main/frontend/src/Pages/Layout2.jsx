import React from 'react'
import Map from './Map'
import { Outlet } from 'react-router'

const Layout2 = () => {
  return (

    <div className='h-screen bg-amber-150'>
        {/* we can add some static element/componet here if we want like eg navbar */}
        <Outlet/>
        {/* we can add some static element/component here if we want like eg footer  */}

    </div>
  )
}

export default Layout2