package me.pr3.uranite.api.feature.module;

public interface IModule {
   String getName();
   IModuleCategory getCategory();
   String getDescription();
   boolean isEnabled();
   void setEnabled(boolean enabled);


}
