import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <div className="hero min-h-screen bg-base-200">
      <div className="hero-content text-center">
        <div className="max-w-xl">
          <h1 className="text-5xl font-bold">GOOGLE SOLUTIONS CHALLENGE</h1>
          <p className="py-6">
            Provident cupiditate voluptatem et in. Quaerat fugiat ut assumenda excepturi exercitationem quasi. In deleniti eaque aut repudiandae et a id nisi.
          </p>
          <Link href="/welcome">
            <button className="btn btn-primary">
              Get Started
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}
