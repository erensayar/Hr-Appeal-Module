import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    jobId: 1
}

export const jobIdReducer = createSlice({
    name: 'jobIdReducer',
    initialState,
    reducers: {
        setJobId: (state, action) => {
            state.jobId = 0
            state.jobId += action.payload
        }
    },
})

export const { setJobId } = jobIdReducer.actions
export default jobIdReducer.reducer