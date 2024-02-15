import axios, { AxiosHeaders } from "axios";


const BASEURL="http://localhost:8080/api/v1/tempMap/"

//reach endpoint to fetch data
export const getAirTemp= async() => {

    try {

        // const res =await axios.get(BASEURL,{
        //     headers: {
        //         'Content-Type': 'application/json'
        //         }}
        //     )
        // return res.data 


        const res =await axios.get(BASEURL)
        return res.data 
        
    } catch (error) {
        

        console.log(error);
        
    }
  
};