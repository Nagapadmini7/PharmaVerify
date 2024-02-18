'use client'
import React, {useState, useEffect} from 'react'


const Page = () => {
  const [allProducts, setAllProducts] = useState([])
  const [isLoaded, setIsLoaded] = useState(false)
  const [currentViewingMode, setcurrentViewingMode] = useState('all')

  useEffect(()=>{
    const fetchData = async () => {
      await fetch('https://dummyjson.com/products?limit=10')
        .then(res => res.json())
        .then((data)=>{
          setAllProducts(data.products)
          setIsLoaded(true)
        })
    }  
    fetchData()
  }, [])

  useEffect(()=>{
    const intervalId = setInterval(() => {
      setcurrentViewingMode(localStorage.getItem('viewingMode'))
    }, 500);

    return ()=>clearInterval(intervalId)

  }, [])

  let cardElements = []
  let pendingElements = []
  let approvedElements = []
  for(const product of allProducts) {
    if (product.id>5){
      approvedElements.push(
          <div className="card w-72 max-h-96 bg-base-100 shadow-xl">
            <figure><img src={product.thumbnail} alt={product.description} /></figure>
            <div className="card-body max-h-72">
              <h2 className="card-title">
                {product.title}
                <div className="badge badge-secondary">${product.price}</div>
              </h2>
              <p>{product.description}</p>
              <div className="card-actions justify-end">
                <div className="badge badge-outline">{product.category}</div>
                <button className="btn btn-primary">Send for Approval</button>
              </div>
            </div>
          </div>
      )
    } else {
      pendingElements.push(
          <div className="card w-72 max-h-96 bg-base-100 shadow-xl">
            <figure><img src={product.thumbnail} alt={product.description} /></figure>
            <div className="card-body max-h-72">
              <h2 className="card-title">
                {product.title}
                <div className="badge badge-secondary">${product.price}</div>
              </h2>
              <p>{product.description}</p>
              <div className="card-actions justify-end">
                <div className="badge badge-outline">{product.category}</div>
                <button className="btn btn-primary">Send for Approval</button>
              </div>
            </div>
          </div>
      )
    }

    cardElements.push(
      <div className="card w-72 max-h-96 bg-base-100 shadow-xl">
        <figure><img src={product.thumbnail} alt={product.description} /></figure>
        <div className="card-body max-h-72">
          <h2 className="card-title">
            {product.title}
            <div className="badge badge-secondary">${product.price}</div>
          </h2>
          <p>{product.description}</p>
          <div className="card-actions justify-end">
            <div className="badge badge-outline">{product.category}</div>
            <button className="btn btn-primary">Send for Approval</button>
          </div>
        </div>
      </div>
    )
  } 

  const skeletons = []
  for(let i = 0; i <8;  i++){
    skeletons.push(
      <div key={i} className="skeleton w-64 h-96"></div>
    )
  }
  
  return (
    <div className='h-auto flex flex-wrap gap-5 w-full p-10 text-black justify-evenly bg-[#C6BC9C]'>
      {(()=>{
        if (isLoaded) {
          switch (currentViewingMode) {
            case 'all':
              return (cardElements)
              break;
            case 'pending':
              return (pendingElements)
              break
            case 'approved':
              return (approvedElements)
              break
          }
        } else{
          return(skeletons)
        }
      })()}
    </div>
  )
}

export default Page
