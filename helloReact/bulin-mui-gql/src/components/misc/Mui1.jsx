import { ThemeProvider } from "@emotion/react";
import { Container, createTheme, Typography } from "@mui/material";
import Button from "@mui/material/Button";
import { makeStyles } from "@mui/styles";
import React from "react";

export const Mui1 = () => {
    return (
        <Container maxWidth="sm">
            <Button variant="contained" color="success">
                Button Bulin
            </Button>
            <Typo1 />
            <Typo2 />
        </Container>
    );
};

const myTheme = createTheme({
    typography: {
        h5: {
            color: "red",
            fontWeight: "bold",
        },
    },
});
const Typo1 = () => (
    <ThemeProvider theme={myTheme}>
        <Typography variant="h5">Typography1 Bulin</Typography>
    </ThemeProvider>
);

const useStyles = makeStyles({
    myFont: {
        background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)",
        color: "white",
    },
});
const Typo2 = () => {
    const classes = useStyles();
    return (
        <Typography variant="h5" className={classes.myFont}>
            Typography2 Bulin
        </Typography>
    );
};
