import { useState } from "react";
import "./App.css";
import VideoUpload from "./components/VideoUpload";

function App() {
  const [videoId, setVideoId] = useState(
    "f35268f5-64fc-458f-8e45-d6cedb937abe"
  );

  return (
    <>
      <div className="flex flex-col items-center space-y-5 justify-center py-0 my-0">
        <h1 className="text-5xl font-extrabold text-gray-400 dark:text-gray-100">
          Welcome to Video Streaming Application
        </h1>
        <div className="flex w-full justify-around py-6 mt-14">
          <div>
            <h1 className="text-white">Playing video</h1>
            <video
              id="video"
              width={500}
              src={`http://localhost:8080/api/v1/videos/stream/${videoId}`}
              controls
            ></video>
          </div>

          <VideoUpload />
        </div>
      </div>
    </>
  );
}

export default App;
