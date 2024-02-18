import React from 'react'
import ClientInteraction from './components/ClientInteraction'
import AdminInteraction from './components/AdminInteraction'

const page = () => {
  return (
    <div className='h-screen flex items-center justify-center bg-[#DBCA9A] text-black'>
      <div className="flex flex-col w-full lg:flex-row">
        <div className="grid flex-grow h-32 card bg-base-300 rounded-box place-items-center">
          {"I want to get my products verified!"}
          <ClientInteraction />
        </div> 
        <div className="divider lg:divider-horizontal">OR</div> 
        <div className="grid flex-grow h-32 card bg-base-300 rounded-box place-items-center">
          {"I'm with the verifying agency!"}
          <AdminInteraction />
        </div>
      </div>
    </div>
  )
}

export default page
