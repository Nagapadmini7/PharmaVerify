'use client'
import { useParams } from 'next/navigation'
import React, { useEffect } from 'react'
import axios from 'axios'

const Page = () => {
  const params = useParams()
  const requestedDrug = params['drug-id']

  useEffect(()=>{
    // const fetchDrugDetails = async () => {
    //   await fetch.get('APIURLGOESHERE')
    // }
    // fetchDrugDetails()
  }, [])

  return (
    <div className='bg-[#C6BC9C] min-h-screen w-screen m-0'>
      {params['drug-id']}
    </div>
  )
}

export default Page
