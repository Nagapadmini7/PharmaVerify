'use client'
import React from 'react'

const AdminInteraction = () => {
  return (
    <div>
          <button className='btn btn-primary' onClick={()=>document.getElementById("admin-login").showModal()}>
            Continue as Admin
          </button>
          <dialog id="admin-login" className="modal p-10">
            <div className="modal-box flex flex-col gap-5">
              <div className='flex flex-col gap-4'>
                <h1 className='text-xl pl-5 text-black'>Login as Admin</h1>
                <label className="form-control w-full justify-center m-auto max-w-xs">
                  <div className="label">
                    <span className="label-text">Admin username?</span>
                  </div>
                  <input type="text" placeholder="Username" className="input input-bordered w-full max-w-xs" />
                  <div className="label">
                    <span className="label-text">Admin password?</span>
                  </div>
                  <input type="text" placeholder="Password" className="input input-bordered w-full max-w-xs" />
                </label>
                <button className='btn btn-secondary w-4/6 m-auto my-5'>
                  Login
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

export default AdminInteraction
