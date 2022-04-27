import { configureStore } from '@reduxjs/toolkit'
import counterReducer from './redux-toolkit-example/CounterReducer'
import jobReducer from './reducer/JobReducer'

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    job: jobReducer
  },
})

