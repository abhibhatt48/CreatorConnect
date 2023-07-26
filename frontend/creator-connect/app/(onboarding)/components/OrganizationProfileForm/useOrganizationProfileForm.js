import { useRef, useState } from "react";

export const useOrganizationProfileForm = () => {
  const fileInputRef = useRef(null);
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageSelect = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setSelectedImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleCircularDivClick = () => {
    fileInputRef.current.click();
  };

  return {
    fileInputRef,
    selectedImage,
    handleImageSelect,
    handleCircularDivClick,
  };
};
