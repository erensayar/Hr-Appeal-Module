import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    jobId: ""
}

export const jobIdReducer = createSlice({
    name: 'jobIdReducer',
    initialState,
    reducers: {
        getJobIdReducer: (state) => {
            console.log("get çalıştı");
            console.log(state);
            console.log(state.jobId);
            return state.jobId;
        },
        setJobIdReducer: (state, action) => {
            state.jobId = ""
            state.jobId += action.payload
            console.log("action.payload: " + action.payload);
            console.log("state.jobId:    " + state.jobId);
        }
    },
})

export const { getJobIdReducer, setJobIdReducer } = jobIdReducer.actions
export default jobIdReducer.reducer