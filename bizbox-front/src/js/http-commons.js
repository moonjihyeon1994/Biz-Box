import axios from "axios";

export default axios.create({
    baseURL: "http://70.12.247.78:8080",
    headers: {
        "Content-type": "application/json"
    }
});