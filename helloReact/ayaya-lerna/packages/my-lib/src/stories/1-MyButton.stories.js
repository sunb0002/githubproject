import React from 'react';
import { action } from '@storybook/addon-actions';
import { text, withKnobs } from '@storybook/addon-knobs';
import MyButton from '../MyButton';

export default {
  title: 'Button',
  component: MyButton,
  decorators: [withKnobs]
};

export const DefaultStyle = () => <MyButton />

export const CustomStyle = () => {
  const knobInput = text("Enter custom text", "Ayaya");
  return <MyButton txt={knobInput} />;
}

