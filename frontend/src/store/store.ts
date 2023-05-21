import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { authSlice } from "./authSlice";
import storage from "redux-persist/lib/storage"
import persistReducer from "redux-persist/es/persistReducer";
import persistStore from "redux-persist/es/persistStore";

const persisistConfig = {
  key: "root",
  version: 1,
  storage,
  whitelist: ["auth"]
}

const rootReducer = combineReducers({
  auth: authSlice.reducer
});

const persistedReducer = persistReducer(persisistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware => getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>

export const persistor = persistStore(store);
