import { configureStore } from '@reduxjs/toolkit'
import counterReducer from './redux-toolkit-example/CounterReducer'
import jobIdReducer from './reducer/JobIdReducer'

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    jobId: jobIdReducer
  },
})

