import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    jobId: ""
}

export const jobIdReducer = createSlice({
    name: 'jobIdReducer',
    initialState,
    reducers: {
        setJobId: (state, action) => {
            state.jobId = ""
            state.jobId += action.payload
        }
    },
})

export const { getJobId, setJobId } = jobIdReducer.actions
export default jobIdReducer.reducer