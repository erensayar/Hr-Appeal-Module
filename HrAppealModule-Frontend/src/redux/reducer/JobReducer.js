import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    jobId: 1,
    jobName: "",
    jobLocation: ""
}

export const jobReducer = createSlice({
    name: 'jobReducer',
    initialState,
    reducers: {
        setJobId: (state, action) => {
            state.jobId = 0
            state.jobId += action.payload
        },
        setJobName: (state, action) => {
            state.jobName = ""
            state.jobName += action.payload
        },
        setJobLocation: (state, action) => {
            state.jobLocation = ""
            state.jobLocation += action.payload
        }
    },
})

export const { setJobId, setJobName, setJobLocation } = jobReducer.actions
export default jobReducer.reducer