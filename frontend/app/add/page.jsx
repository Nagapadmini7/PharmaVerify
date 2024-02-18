'use client'
import React, {useEffect, useState} from 'react'

const Page = () => {
  const [drugName, setDrugName] = useState('')
  const [ingredientsArray, setingredientsArray] = useState([])
  const [currentIngName, setCurrentIngName] = useState('')
  const [currentIngredientComposition, setCurrentIngredientComposition] = useState(0)
  const [currentCompositionType, setCurrentCompositionType] = useState('')


  const [childElements, setChildElements] = useState([
    <div className='join w-auto gap-2' id='ingredients' key={0}>
      <label className="input input-bordered flex items-center gap-2 w-52">
        <input type="text" className="grow join-item" placeholder="Ingredient" onChange={(e)=>setCurrentIngName(e.target.value)} />
      </label>
      <label className="input input-bordered flex items-center gap-2 w-28">
        <input type='number' className="grow join-item" style={{WebkitAppearance: 'none', MozAppearance: 'textfield'}} placeholder="Qty." onChange={(e)=>setCurrentIngredientComposition(e.target.value)}/>
      </label>
      <select className="select w-full max-w-xs" onChange={(e)=>setCurrentCompositionType(e.target.value
          )}>
        <option selected>Composition Type</option>
        <option>mg</option>
        <option>%age</option>
      </select>
    </div>   
  ])


  // Step 2: Function to add a new child element
  useEffect(()=>{
    const addIngredient = () => {
      setCurrentCompositionType('')
      setCurrentIngredientComposition(0)
      setCurrentIngName('')
      
      console.log("cleared. hopefully.")
      console.log("current Values are: ", drugName, currentIngName, currentCompositionType, currentIngredientComposition)
  
      const newChild =       
      <div className='join w-auto gap-2' id='ingredients' key={childElements.length}>
        <label className="input input-bordered flex items-center gap-2 w-52">
          <input type="text" className="grow join-item" placeholder="Ingredient" onChange={(e)=>setCurrentIngName(e.target.value)} />
        </label>
        <label className="input input-bordered flex items-center gap-2 w-28">
          <input type='number' className="grow join-item" style={{WebkitAppearance: 'none', MozAppearance: 'textfield'}} placeholder="Qty." onChange={(e)=>setCurrentIngredientComposition(e.target.value)}/>
        </label>
        <select className="select w-full max-w-xs"  onChange={(e)=>setCurrentCompositionType(e.target.value)}>
          <option selected disabled>Composition Type</option>
          <option>mg</option>
          <option>%age</option>
        </select>
      </div>
  
      setChildElements([...childElements, newChild]);
      console.log("added new child")
    }

    document.getElementById('addIngredient').addEventListener('click', addIngredient)

    return () => {
      document.removeEventListener('click', addIngredient);
    }

  }, [currentIngName, currentCompositionType, currentIngredientComposition])

  const submitForApproval = () => {
    const newIngredient = {
      'ingredientName': currentIngName,
      'compositionValue': currentIngredientComposition,
      'compositionType': currentCompositionType
    }

    console.log('current: ', newIngredient)
    console.log('current ingredient list: ', ingredientsArray)

    setingredientsArray([...ingredientsArray, newIngredient])

    console.log('all ingredients, ', ingredientsArray)

  }

  return (
    <div className='w-full h-screen flex items-center justify-center bg-[#C6BC9C]'>
      <div className='w-auto border-4 border-[#9e977e] p-4 rounded-2xl gap-4 text-black flex flex-col'>
        <h1 className='text-xl underline font-medium'>Add Drug</h1>
        <label className="input input-bordered flex items-center gap-2">
          <input type="text" className="grow" value={drugName} onChange={(e)=>setDrugName(e.target.value)} placeholder="Drug Name" />
        </label>
        <div role="alert" className="alert">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" className="stroke-info shrink-0 w-6 h-6"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
          <span>Add your ingredients below.</span>
        </div>
        {childElements}
        <div className='flex flex-row w-full justify-end gap-2'>
          <button className='btn btn-accent' id='addIngredient'>Add ingredient</button>
          <button className='btn btn-primary' onClick={submitForApproval}>Submit for approval</button>
        </div>
      </div>
    </div>
  )
}

export default Page
