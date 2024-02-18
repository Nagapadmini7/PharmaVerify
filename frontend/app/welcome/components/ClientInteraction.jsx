'use client'
import React, {useState, useEffect} from 'react'

const ClientInteraction = () => {
  const [UserRegistration, setUserRegistration] = useState(false)
  return (
    <div>
          <button className='btn btn-primary' onClick={()=>document.getElementById('client-login').showModal()}>
            Continue as client
          </button>
          <dialog id="client-login" className="modal">
          <div className="modal-box flex flex-col gap-5">
              <div className='flex flex-col gap-4'>
                <div className='pl-5 flex flex-col gap-2'>
                  <h1 className='text-xl text-black'>Login as Client</h1>
                  {
                    UserRegistration ? (
                      <span className='cursor-pointer link-hover link-accent w-fit' onClick={()=>setUserRegistration(false)}>Go back to login</span>
                    ) : (
                      <span className='cursor-pointer link-hover link-accent w-fit' onClick={()=>setUserRegistration(true)}>Not registered yet? Click here</span>
                    )
                  }
                </div>
                <label className="form-control w-full justify-center m-auto max-w-xs">
                  <div className="label">
                    <span className="label-text">Client username?</span>
                  </div>
                  <input type="text" placeholder="Username" className="input input-bordered w-full max-w-xs" />
                  {UserRegistration && 
                    <>
                      <div className="label">
                        <span className="label-text">Client email?</span>
                      </div>
                      <input type="text" placeholder="Email" className="input input-bordered w-full max-w-xs" />                    
                    </>
                  }
                  <div className="label">
                    <span className="label-text">Client password?</span>
                  </div>
                  <input type="text" placeholder="Password" className="input input-bordered w-full max-w-xs" />
                </label>
                <button className='btn btn-secondary w-4/6 m-auto my-5'>
                  {UserRegistration ? 'Register' : 'Login'}
                </button>                
              </div>
              <div className="modal-action">
                <form method="dialog">
                  {/* if there is a button in form, it will close the modal */}
                  <button className="btn">Close</button>
                </form>
              </div>
            </div>
          </dialog>      
    </div>
  )
}

export default ClientInteraction
