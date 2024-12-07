import {} from "react";
import "./App.css";
import VideoUpload from "./components/VideoUpload";

function App() {
  return (
    <>
      <div className="flex flex-col items-center space-y-5 justify-center py-0 my-0">
        <h1 className="text-5xl font-extrabold text-gray-400 dark:text-gray-100">
          Welcome to Video Streaming Application
        </h1>

        <VideoUpload />
      </div>
    </>
  );
}

export default App;
