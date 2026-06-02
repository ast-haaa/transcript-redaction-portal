
import { useState } from "react";
import axios from "axios";

import "./App.css";

function App() {

  const [file, setFile] =
    useState(null);

  const [result, setResult] =
    useState(null);

  const [loading, setLoading] =
    useState(false);

  const uploadFile = async () => {

    if (!file) {

      alert("Please select a file");

      return;
    }

    try {

      setLoading(true);

      const formData =
        new FormData();

      formData.append("file", file);

      const response =
        await axios.post(
          "http://localhost:8080/api/upload",
          formData
        );

      setResult(response.data);

    } 

catch (error) {

  console.log(error);

  if(error.response &&
     error.response.data){

    alert(error.response.data);
  }

  else{

    alert(
      "Something went wrong"
    );
  }
}


    setLoading(false);
  };

  const copyText = () => {

    navigator.clipboard.writeText(
      result.redactedText
    );

    alert("Copied!");
  };

  const downloadText = () => {

    const blob =
      new Blob(
        [result.redactedText],
        { type: "text/plain" }
      );

    const link =
      document.createElement("a");

    link.href =
      URL.createObjectURL(blob);

    link.download =
      "redacted_transcript.txt";

    link.click();
  };

  return (

    <div className="container">

      <h1 className="title">
        Transcript Redaction Portal
      </h1>

<p className="subtitle">

  Securely upload transcripts and automatically
  redact sensitive information using AI-powered
  processing.

</p>



      <div className="upload-card">

        <h2>
          Upload Transcript
        </h2>

        <div className="file-input-wrapper">

  <input
    className="file-input"
    type="file"
    onChange={(e)=>
      setFile(e.target.files[0])
    }
  />

</div>

        <button
          className="main-btn"
          onClick={uploadFile}
        >
          Process File
        </button>

        {
          loading &&
          <div className="loading">
            Processing Transcript...
          </div>
        }

      </div>

      {
        result && (

          <>

            <div className="card">

              <h2>
                Redacted Transcript
              </h2>

              <div className="action-buttons">

                <button
                  className="main-btn"
                  onClick={copyText}
                >
                  Copy Text
                </button>

                <button
                  className="main-btn"
                  onClick={downloadText}
                >
                  Download
                </button>

              </div>


           
<div className="transcript-box">

  {
    result.redactedText
      .split("\n")
      .map((line, index) => {

        const words =
          line.split(" ");

        return (

          <div
            key={index}
            className="transcript-line"
          >

            {
              words.map((word, i) => {

                if(
                  word.includes("Analyst") ||
                  word.includes("CFO")
                ){

                  return (

                    <span
                      key={i}
                      className="speaker"
                    >
                      {word}{" "}
                    </span>
                  );
                }

                if(word.includes("[PERSON_NAME]")){

                  return (

                    <span
                      key={i}
                      className="person-highlight"
                    >
                      {word}{" "}
                    </span>
                  );
                }

                if(word.includes("[EMAIL]")){

                  return (

                    <span
                      key={i}
                      className="email-highlight"
                    >
                      {word}{" "}
                    </span>
                  );
                }

                if(word.includes("[PHONE_NUMBER]")){

                  return (

                    <span
                      key={i}
                      className="phone-highlight"
                    >
                      {word}{" "}
                    </span>
                  );
                }

                if(word.includes("[FINANCIAL_FIGURE]")){

                  return (

                    <span
                      key={i}
                      className="finance-highlight"
                    >
                      {word}{" "}
                    </span>
                  );
                }

                return (

                  <span key={i}>
                    {word}{" "}
                  </span>
                );
              })
            }

          </div>
        );
      })
  }

</div>



            </div>

            <div className="card">

              <h2>
                Summary
              </h2>

              <div className="summary-box">

                <div className="section-label">
                  AI Generated Summary
                </div>

                <p className="summary-text">
                  {result.summary}
                </p>

              </div>

            </div>

          
<div className="card">

  <h2>
    Key Points
  </h2>

  <div className="summary-box">

    <div className="section-label">
      Important Discussion Points
    </div>

    <div className="points-grid">

      {
        result.keyPoints
          .split("•")
          .filter(
            point =>
              point.trim() !== ""
          )
          .map((point, index) => (

            <div
              key={index}
              className="point-card"
            >

              <div className="point-number">
                0{index + 1}
              </div>

              <div className="point-text">
                {point.trim()}
              </div>

            </div>
          ))
      }

    </div>

  </div>

</div>



            <div className="card">

              <h2>
                Audit Logs
              </h2>

              <table>

                <thead>

                  <tr>

                    <th>
                      Original
                    </th>

                    <th>
                      Replacement
                    </th>

                    <th>
                      Reason
                    </th>

                  </tr>

                </thead>

                <tbody>

                  {
                    result.auditLogs.map(
                      (log, index) => (

                        <tr key={index}>

                          <td>
                            {log.original}
                          </td>

                          <td>

                            <span className="badge">
                              {log.replacement}
                            </span>

                          </td>

                          <td>
                            {log.reason}
                          </td>

                        </tr>
                      ))
                  }

                </tbody>

              </table>

            </div>

          </>
        )
      }

    </div>
  );
}

export default App;
